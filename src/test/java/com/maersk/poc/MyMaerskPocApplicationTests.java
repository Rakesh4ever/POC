package com.maersk.poc;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebFluxTest
class MyMaerskPocApplicationTests {
	
	private WebTestClient webTestClient;
			
	@Before
	public void before() {
		this.webTestClient= WebTestClient.bindToServer().baseUrl("http://localhost:8080").build();
	}	
	
	 @Test
	    public void testGetAllBookingDetails() throws Exception {
	        this.webTestClient.get().uri("/api/bookings")
	                          .accept(MediaType.APPLICATION_JSON_UTF8)
	                          .exchange()
	                          .expectStatus()
	                          .isOk();
	        		
	           /*.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
	           .andExpect(status().isOk())
	           .andExpect(content().contentType("application/json"));*/

	    }

	
	  @Test void contextLoads() {
	  
	  }
	 

}
