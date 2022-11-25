package com.maersk.poc.repository;


import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.maersk.poc.model.Booking;


public interface ReactiveBookingRepository extends ReactiveCrudRepository<Booking, Integer> {
	/*
	 * Flux<Booking> findByAge(Integer bookingRef);
	 * 
	 * 
	 * Flux<Booking> findByLastname(String destination);
	 * 
	 * @Query("SELECT * FROM booking WHERE origin = ?0 and destination  = ?1")
	 * Mono<Booking> findByFirstnameAndLastname(String firstname, String lastname);
	 * 
	 * // for deferred execution Flux<Booking> findByDestination(Mono<String>
	 * destination);
	 * 
	 * Mono<Booking> findByOriginAndDestination(Mono<String> origin, String
	 * destination);
	 * 
	 */
}
