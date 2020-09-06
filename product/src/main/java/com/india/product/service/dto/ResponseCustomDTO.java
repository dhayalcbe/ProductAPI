package com.india.product.service.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCustomDTO {

	private Long groupId;
	private double rating;
	List<ReviewDTO> reviews;

}
