package com.spring.productapi.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.spring.productapi.dto.ReviewByProductResponse;
import com.spring.productapi.dto.ReviewRequest;
import com.spring.productapi.dto.SuccessResponse;

@Component
public class ProductRestClientImpl implements ProductRestClient {

	@Value("${com.spring.reviews.resturl}")
	private String REVIEW_REST_API;

	@Override
	public SuccessResponse addReview(ReviewRequest reviewRequest) {
		RestTemplate restTemplate = new RestTemplate();
		System.out.println("Client");
		SuccessResponse response = restTemplate.postForObject(REVIEW_REST_API, reviewRequest, SuccessResponse.class);
		return response;
	}
	
	@Override
	public ReviewByProductResponse getProduct(long productId) {
		RestTemplate restTemplate = new RestTemplate();
		ReviewByProductResponse response = restTemplate.getForObject(REVIEW_REST_API+"/"+productId, ReviewByProductResponse.class);
		return response;
	}

}
