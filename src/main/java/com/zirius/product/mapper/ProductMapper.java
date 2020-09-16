package com.zirius.product.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.zirius.product.dto.ProductDTO;
import com.zirius.product.repository.model.Product;

@Mapper(config = ProductMapperConfig.class)
public interface ProductMapper {

	Product toProduct(ProductDTO productDTO);
	
	List<Product> toProducts(List<ProductDTO> productDTOs);
}
