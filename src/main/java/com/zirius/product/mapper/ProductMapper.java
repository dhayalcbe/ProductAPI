package com.zirius.product.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.zirius.product.dto.ProductDTO;
import com.zirius.product.dto.ProductDetailsDTO;
import com.zirius.product.dto.ProductReviewDTO;
import com.zirius.product.repository.model.Product;
import com.zirius.product.review.dto.ReviewDTO;

@Mapper(config = ProductMapperConfig.class)
public interface ProductMapper {

	Product toProduct(ProductDTO productDTO);
	
	void toProduct(@MappingTarget Product product, ProductDTO productDTO);

	List<Product> toProducts(List<ProductDTO> productDTOs);

	ProductDTO toProductDTO(Product product);

	List<ProductDTO> toProductDTOs(List<Product> products);
	
	ReviewDTO toReviewDTO(ProductReviewDTO productReviewDTO);
	
	default List<ReviewDTO> toReviewDTOs(Long reviewGroupId, List<ProductReviewDTO> productReviewDTOs){
		List<ReviewDTO> reviewDTOs = new ArrayList<>();
		productReviewDTOs.forEach(productDTO -> {
			ReviewDTO reviewDTO = toReviewDTO(productDTO);
			reviewDTO.setReviewGroupId(reviewGroupId);
			reviewDTOs.add(reviewDTO);
		});
		return reviewDTOs;
	}
	
	ProductReviewDTO toProductReviewDTO(ReviewDTO reviewDTO);
	
	List<ProductReviewDTO> toProductReviewDTOs(List<ReviewDTO> reviewDTOs);
	
	ProductDetailsDTO toProductDetailsDTO(Product product);
	
}
