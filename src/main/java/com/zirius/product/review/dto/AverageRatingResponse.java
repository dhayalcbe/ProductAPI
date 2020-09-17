package com.zirius.product.review.dto;

import org.springframework.http.HttpStatus;

import com.zirius.product.error.ErrorDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AverageRatingResponse {

	private String averageRating;

	private String message;

	private HttpStatus status;

	private ErrorDetails errors;

}
