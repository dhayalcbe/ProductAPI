package com.zirius.product.review.dto;

import java.util.List;

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
public class ReviewResponse {

	private List<ReviewDTO> reviews;

	private String message;

	private HttpStatus status;

	private ErrorDetails errors;

}
