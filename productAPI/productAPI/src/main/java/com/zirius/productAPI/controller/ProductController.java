package com.zirius.productAPI.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zirius.productAPI.entity.Product;
import com.zirius.productAPI.service.ProductService;


@RestController
public class ProductController {

	@Autowired
	ProductService pser;
	
	@PostMapping("/addProduct")
	public void addUpdateProduct(@RequestBody Product p){

		pser.addUpdateProduct(p);

	}

	@PostMapping("/addProductReview")
	public void addProductReview(@RequestBody HashMap map){
		
		Integer productId = (Integer) map.get("productId");
		HashMap reviewDetails = (HashMap) map.get("reviewDetails");
		
		pser.addProductReview(productId, reviewDetails);
	}
	
	@GetMapping("/getProduct/{productId}")
	public HashMap getProductDetails(@PathVariable Integer productId){
		
		return pser.getProductInfo(productId);
		
	}
	
}
