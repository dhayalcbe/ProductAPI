package com.spring.productapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.productapi.dto.ProductRequest;
import com.spring.productapi.dto.ProductSuccessResponse;
import com.spring.productapi.dto.ReviewByProductResponse;
import com.spring.productapi.dto.ReviewRequest;
import com.spring.productapi.dto.SuccessResponse;
import com.spring.productapi.integration.ProductRestClient;
import com.spring.productapi.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService service;
	
	@Autowired
	ProductRestClient restClient;
	
	@PostMapping("/product")
	public ProductSuccessResponse createProduct(@RequestBody ProductRequest request){
		return service.addProduct(request);
	}
	
	@PostMapping("/product/review")
	public SuccessResponse createReview(@RequestBody ReviewRequest review){
		System.out.println("Controller");
		return restClient.addReview(review);
	}
	
	@GetMapping("/product/{id}")
	public ReviewByProductResponse getResponse(@PathVariable("id") long productId){
		return restClient.getProduct(productId);
	}
	
}
