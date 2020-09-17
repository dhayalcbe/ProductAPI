package com.zirius.product.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.zirius.product.util.Constants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductReviewsDTO {

	@Valid
	@NotEmpty(message = Constants.REVIEWS_IS_MANDATORY)
	private List<ProductReviewDTO> reviews;
	
}
