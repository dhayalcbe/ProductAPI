package com.india.product.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

	private long id;

	private String name;

	private String type;

	private double price;

	@JsonIgnore
	private byte[] image;

	private Long reviewGroupId;

	// Review API purpose

	@JsonIgnore
	private String comments;

	@JsonIgnore
	private long starRating;

	private ResponseCustomDTO customDTO;

}
