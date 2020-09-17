package com.zirius.product.dto;

import java.util.List;

import com.zirius.product.util.ProductType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailsDTO {

	private Long id;
	
	private String name;
	
	private ProductType type;
	
	private Double price;
	
	private Byte[] image;
	
	private String description;
	
	private List<ProductReviewDTO> reviews;
	
	private String averageRating;
	
}
