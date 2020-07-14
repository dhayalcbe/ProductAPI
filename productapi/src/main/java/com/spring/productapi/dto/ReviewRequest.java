package com.spring.productapi.dto;

public class ReviewRequest {
	private long productId;
	private String comments;
	private int ratings;

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getRatings() {
		return ratings;
	}

	public void setRatings(int ratings) {
		this.ratings = ratings;
	}

	@Override
	public String toString() {
		return "ReviewRequest [comments=" + comments + ", ratings=" + ratings + "]";
	}

}

