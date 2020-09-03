package com.example.demo.configuration;

import org.springframework.web.client.RestTemplate;

public class ReviewRestClient {
	private String REQUEST_URI;
	private RestTemplate restTemplate;

	public ReviewRestClient(RestTemplate restTemplate, String url) {
		this.restTemplate = restTemplate;
		this.REQUEST_URI = url ;
	}



	public String getREQUEST_URI() {
		return REQUEST_URI;
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}
	
	
	
	
		
}
