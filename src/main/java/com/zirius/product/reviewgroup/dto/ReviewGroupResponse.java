package com.zirius.product.reviewgroup.dto;

import org.springframework.http.HttpStatus;

import com.zirius.product.error.ErrorDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewGroupResponse {
	
	private String message;

	private HttpStatus status;

	private ErrorDetails errors;

	private Long reviewGroupId;
	
}
