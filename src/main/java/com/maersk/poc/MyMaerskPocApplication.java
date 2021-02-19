package com.maersk.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class MyMaerskPocApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(MyMaerskPocApplication.class, args);
	}

	
	@Bean
	public WebClient.Builder getWebClient(){
		return WebClient.builder();
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate() ;
		
	}
}
