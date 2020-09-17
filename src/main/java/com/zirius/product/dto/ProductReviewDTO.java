package com.zirius.product.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.zirius.product.review.dto.StarRating;
import com.zirius.product.util.Constants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductReviewDTO {

	@NotBlank(message = Constants.COMMENTS_MANDATORY)
	private String comments;
	
	@NotNull(message = Constants.STAR_RATING_MANDATORY)
	private StarRating starRating;
}
