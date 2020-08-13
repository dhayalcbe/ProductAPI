package com.interview.product.controller;

import com.interview.product.model.ProductRequestModel;
import com.interview.product.model.ProductResponeModel;
import com.interview.review.models.ReviewModel;
import com.interview.product.service.ProductApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductApiRestController {

    @Autowired
    ProductApiService productApiService;

    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@RequestBody ProductRequestModel productModel){
        productApiService.createProduct(productModel);
        return ResponseEntity.ok("Product Created");
    }
    @PutMapping("/edit/{productId}")
    public ResponseEntity<String> editProduct(@PathVariable int productId,@RequestBody ProductRequestModel productModel){
        productApiService.editProduct(productId,productModel);
        return ResponseEntity.ok("Product edited");
    }

    @PostMapping("/addreview/{productId}")
    public ResponseEntity<String> addProductReview(@PathVariable int productId,@RequestBody List<ReviewModel> reviews){
        productApiService.addProductReview(productId,reviews);
        return ResponseEntity.ok("Review Added");
    }

    @GetMapping("/get/{productId}")
    public ResponseEntity<ProductResponeModel> getProduct(@PathVariable int productId){
       return ResponseEntity.ok().body(productApiService.getProductDetails(productId));
    }

}
