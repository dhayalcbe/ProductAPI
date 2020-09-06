package com.india.product.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
	private Long id;

	private String comments;

	private long startRating;

	private long groupId;
}
