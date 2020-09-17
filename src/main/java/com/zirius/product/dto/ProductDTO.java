package com.zirius.product.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.zirius.product.util.Constants;
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
		
	@NotEmpty(message = Constants.NAME_SHOULD_NOT_BE_BLANK)
	private String name;
	
	@NotNull(message = Constants.TYPE_SHOULD_NOT_BE_NULL)
	private ProductType type;
	
	@NotNull(message = Constants.PRICE_SHOULD_NOT_BE_NULL)
	private Double price;
	
	private Byte[] image;
	
	private String description;
	
}
