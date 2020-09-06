package com.india.product.service.mapper;

import org.mapstruct.Mapper;

import com.india.product.entity.Product;
import com.india.product.service.dto.ProductDTO;

@Mapper(componentModel = "spring", uses = {})
public interface ProductMapper extends BaseMapper<ProductDTO, Product> {
	default Product fromId(Long id) {
		if (id == null) {
			return null;
		}
		Product product = new Product();
		product.setId(id);
		return product;
	}
}
