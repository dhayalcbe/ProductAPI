package com.zirius.product.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zirius.product.dto.ProductDTO;
import com.zirius.product.dto.ProductDetailsResponse;
import com.zirius.product.dto.ProductResponse;
import com.zirius.product.dto.ProductReviewResponse;
import com.zirius.product.dto.ProductReviewsDTO;
import com.zirius.product.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Validated
public class ProductResource {

	@Autowired
	private ProductService productService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductDTO productDTO) {
		return productService.createProduct(productDTO);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id,
			@RequestBody @Valid ProductDTO productDTO) {
		return productService.updateProduct(id, productDTO);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDetailsResponse> getProductDetails(@PathVariable Long id) {
		return productService.getProductDetails(id);
	}

	@PostMapping(value = "/{id}/reviews", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductReviewResponse> createReviews(@PathVariable Long id,
			@RequestBody @Valid ProductReviewsDTO productReviewsDTO) {
		return productService.createReviews(id, productReviewsDTO);
	}

}
