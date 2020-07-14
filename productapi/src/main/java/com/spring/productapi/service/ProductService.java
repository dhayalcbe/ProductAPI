package com.spring.productapi.service;

import com.spring.productapi.dto.ProductRequest;
import com.spring.productapi.dto.ProductSuccessResponse;

public interface ProductService {

	public ProductSuccessResponse addProduct(ProductRequest request);
}
