package com.maersk.poc.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.maersk.poc.model.Booking;
import com.maersk.poc.model.CheckAvailability;
import com.maersk.poc.repository.ReactiveBookingRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api")
public class BookingController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	@Autowired
	private ReactiveBookingRepository bookingRepository;

	@GetMapping("/bookings")
	public Flux<Booking> getAllBookingDetails() {
		return bookingRepository.findAll();
	}

	@PostMapping("/booking")
	public Mono<Booking> createCustomer(@Valid @RequestBody Booking booking) {
		
		Long primaryKey=9570000001L;
		//create primary key auto generated by one per request
		CheckAvailability avail=restTemplate.getForObject("http://localhost:9001/api/bookings/status", CheckAvailability.class);
		Integer spaces=avail.getAvailableSpace();
		
		primaryKey=primaryKey+spaces;
		//Timestamp in String ISO-8601 Date and Time format
		// Input
		Date date = new Date(System.currentTimeMillis());

		// Conversion
		SimpleDateFormat sdf;
		sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));//other TimeZone :GMT+5:30,CET
		String indatimestamp = sdf.format(date);

		// Output
		// "2017-02-16T21:00:00.000+01:00"
		
		booking.setBookingRef(primaryKey);
		booking.setTimestamp(indatimestamp);
		return bookingRepository.save(booking);
	}

	@PutMapping("/booking/{id}")
	public Mono<ResponseEntity<Booking>> updateBooking(@PathVariable("id") Integer id, @RequestBody Booking booking) {
		return bookingRepository.findById(id).flatMap(bookingData -> {
			/*
			 * customerData.setName(customer.getName());
			 * customerData.setAge(customer.getAge());
			 * customerData.setActive(customer.isActive());
			 */
			bookingData.setBookingRef(booking.getBookingRef());
			bookingData.setContainersize(booking.getContainersize());
			bookingData.setDestination(booking.getDestination());
			bookingData.setOrigin(booking.getOrigin());
			bookingData.setQuantity(booking.getQuantity());
			bookingData.setTimestamp(booking.getTimestamp());
			return bookingRepository.save(bookingData);
		}).map(updateBooking -> new ResponseEntity<>(updateBooking, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	

	@GetMapping("/bookings/checkAvailable")
	public  Mono<CheckAvailability> getAvailabilty(CheckAvailability chk) {
		Mono<CheckAvailability> availability=  webClientBuilder.build()
						                .get()
						                .uri("http://localhost:9001/api/bookings/status")
						                .retrieve()
						                .bodyToMono(CheckAvailability.class)
						                //.block();
						                .map(temp -> {chk.setAvailableSpace(temp.getAvailableSpace());
						                              chk.setAvailable(temp.getAvailable());
						                    
						                    return chk;
						                });
	
		
		return availability;
	  
	  } 

	/*
	 * @DeleteMapping("/booking/{id}") public ResponseEntity<String>
	 * deleteCustomer(@PathVariable("id") UUID id) { try {
	 * bookingRepository.deleteById(id).subscribe(); } catch (Exception e) { return
	 * new ResponseEntity<>("Fail to delete!", HttpStatus.EXPECTATION_FAILED); }
	 * return new ResponseEntity<>("Customer has been deleted!", HttpStatus.OK); }
	 */
	@DeleteMapping("/booking/delete")
	public ResponseEntity<String> deleteAllBooking() {
		try {
			bookingRepository.deleteAll().subscribe();
		} catch (Exception e) {
			return new ResponseEntity<>("Fail to delete!", HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<>("All customers have been deleted!", HttpStatus.OK);
	}
	/*
	 * @GetMapping("/booking/findbyage") public Flux<Booking>findByAge(@RequestParam
	 * int age) { return bookingRepository.findByAge(age); }
	 */

}