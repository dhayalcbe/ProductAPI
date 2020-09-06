package com.india.product.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.india.product.entity.Product;
import com.india.product.service.ProductService;
import com.india.product.service.dto.ProductDTO;

@RestController
@RequestMapping("/api")
public class ProductResource {

	@Autowired
	ProductService productService;

	@PostMapping("/createProduct")
	public Product createProduct(@RequestParam("file") MultipartFile file, @RequestParam("jsonString") String json)
			throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ProductDTO productDTO = mapper.readValue(json, ProductDTO.class);
		return productService.save(file, productDTO);
	}

	@PostMapping("/createReview")
	public boolean createReview(@RequestBody ProductDTO productDTO) throws Exception {

		return productService.restCallToReviewService(productDTO);
	}

	@GetMapping("/getProductById/{productId}")
	public ProductDTO getProductById(@PathVariable Long productId) throws Exception {
		return productService.getProductDetails(productId);
	}
}
