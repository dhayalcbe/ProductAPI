package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;

import com.example.demo.configuration.ReviewRestClient;
import com.example.demo.model.Product;
import com.example.demo.model.ProductExtendendedDTO;
import com.example.demo.model.ProductReviewComposite;
import com.example.demo.model.Review;
import com.example.demo.repo.ProductCompositeRepo;
import com.example.demo.repo.ProductRepo;

@Repository
public class ProductService {

	@Autowired
	private ReviewRestClient reviewRestClient;

	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private ProductCompositeRepo productCompositeRepo;

	public Product createProduct(Product product) {
		return productRepo.save(product);
	}

	public void createProductReview(Long productId, Review review) {
		MultiValueMap<String, String> headers = new HttpHeaders();
		headers.add("User-Agent", "EmployeeRestClient demo class");
		headers.add("Accept-Language", "en-US");
		HttpEntity<Review> entity = new HttpEntity<>(review, headers);
		ResponseEntity<Review> reviewResponse = reviewRestClient.getRestTemplate()
				.postForEntity(reviewRestClient.getREQUEST_URI() +"/api/reviews/create", entity, Review.class);
		Review reviewInfo = reviewResponse.getBody();
		productCompositeRepo.save(new ProductReviewComposite(productId, reviewInfo.getId()));
	}

	public ProductExtendendedDTO findProductInfo(Long productId) {
		ProductExtendendedDTO prod = null;
		List<Integer> reviewId = productCompositeRepo.findReviewIdByproductID(productId);
		List<Review> reviews = new ArrayList<>();
		for (Integer integer : reviewId) {
			Review review = reviewRestClient.getRestTemplate().getForObject(
					reviewRestClient.getREQUEST_URI() + "/api/reviews/findreview/{reviewId}", Review.class, integer);
			reviews.add(review);
		}
		Product product = productRepo.findById(productId).get();
		prod = new ProductExtendendedDTO();
		prod.setProduct(product);
		prod.setReview(reviews);
		return prod;
	}

}
