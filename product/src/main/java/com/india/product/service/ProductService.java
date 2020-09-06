package com.india.product.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.india.product.entity.Product;
import com.india.product.service.dto.ProductDTO;

public interface ProductService {

	ProductDTO getProductDetails(Long productId);

	Product save(MultipartFile file, ProductDTO productDTO) throws IOException;

	boolean restCallToReviewService(ProductDTO productDTO);

}
