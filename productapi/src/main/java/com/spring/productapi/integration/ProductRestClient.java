package com.spring.productapi.integration;

import com.spring.productapi.dto.ReviewByProductResponse;
import com.spring.productapi.dto.ReviewRequest;
import com.spring.productapi.dto.SuccessResponse;

public interface ProductRestClient {
	public SuccessResponse addReview(ReviewRequest reviewRequest);
	
	public ReviewByProductResponse getProduct(long productId);
}
