package com.zirius.product.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.zirius.product.util.ProductType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

	private Long id;
	
	private Long reviewGroupId;
	
	@NotEmpty
	private String name;
	
	@NotNull
	private ProductType type;
	
	@NotNull
	private Double price;
	
	private Byte[] image;
	
	private String description;
	
}
