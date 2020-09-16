package com.zirius.product.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.zirius.product.dto.ProductResponse;
import com.zirius.product.dto.ProductsDTO;
import com.zirius.product.mapper.ProductMapper;
import com.zirius.product.repository.ProductRepository;
import com.zirius.product.repository.model.Product;
import com.zirius.product.reviewgroup.dto.ReviewGroup;
import com.zirius.product.reviewgroup.dto.ReviewGroupResponse;

@Service
public class ProductService {
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Transactional
	public ResponseEntity<ProductResponse> createProduct(ProductsDTO productsDTO) {
		productsDTO.getProducts().forEach(productDTO -> {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<ReviewGroupResponse> reviewGroupResponseEntity = restTemplate.postForEntity(
					"http://localhost:8081/api/review-group", ReviewGroup.builder().topic(productDTO.getName()).build(),
					ReviewGroupResponse.class);
			ReviewGroupResponse reviewGroupResponse = reviewGroupResponseEntity.getBody();
			if(null != reviewGroupResponse && reviewGroupResponseEntity.getStatusCodeValue() == 201) {
				productDTO.setReviewGroupId(reviewGroupResponse.getReviewGroupId());
				Product product = productMapper.toProduct(productDTO);
				productRepository.save(product);
			}
		});
		return null;
	}

}
