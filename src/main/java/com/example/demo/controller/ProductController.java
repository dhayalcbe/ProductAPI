package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.model.ProductExtendendedDTO;
import com.example.demo.model.Review;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/create")
	public Product createProduct(@RequestBody Product review) {
		return productService.createProduct(review);

	}

	@PostMapping("/review/create/{productId}")
	public void createProductReview(@PathVariable Long productId, @RequestBody Review review) {
		productService.createProductReview(productId, review);

	}
	
	
	@GetMapping("/findAll/productInfo/{productId}")
	public ProductExtendendedDTO createProductReview(@PathVariable Long productId) {
		return productService.findProductInfo(productId);

	}

}
