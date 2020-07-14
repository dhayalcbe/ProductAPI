package com.spring.productapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.spring.productapi.dto.ProductRequest;
import com.spring.productapi.dto.ProductSuccessResponse;
import com.spring.productapi.model.Product;
import com.spring.productapi.repo.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Value("${com.spring.product.success.msg}")
	private String PRODUCT_SUCCESS_MSG ;
	
	@Autowired
	ProductRepository repository;
	
	@Override
	public ProductSuccessResponse addProduct(ProductRequest request) {
		Product product = new Product();
		product.setPrice(request.getPrice());
		product.setProductName(request.getProductName());
		product.setProductType(request.getProductType());
		product.setSpecification(request.getSpecification());
		Product savedProduct = repository.save(product);
		ProductSuccessResponse response = new ProductSuccessResponse();
		response.setProductId(savedProduct.getId());
		response.setProductName(savedProduct.getProductName());
		response.setMessage(PRODUCT_SUCCESS_MSG);
		return response;
	}

}
