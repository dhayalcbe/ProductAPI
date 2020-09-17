package com.zirius.product.review.dto;

import java.util.HashMap;
import java.util.Map;

public enum StarRating {

	ONE_STAR(1),
	TWO_STAR(2),
	THREE_STAR(3),
	FOUR_STAR(4),
	FIVE_STAR(5);
	
	public static final Map<Integer, StarRating> StarRatingMap = new HashMap<>();
	
	static {
		for(StarRating rating : StarRating.values()) {
			StarRatingMap.put(rating.value, rating);
		}
	}
	
	private final int value;
	
	StarRating(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
