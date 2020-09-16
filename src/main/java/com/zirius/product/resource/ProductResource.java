package com.zirius.product.resource;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zirius.product.dto.ProductResponse;
import com.zirius.product.dto.ProductsDTO;
import com.zirius.product.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Validated
public class ProductResource {

	@Autowired
	private ProductService productService;

	@PostMapping
	public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid @NotNull ProductsDTO productsDTO) {
		return productService.createProduct(productsDTO);
	}
}
