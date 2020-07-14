package com.spring.productapi.dto;

import java.util.List;

public class ReviewByProductResponse {

	private long productId;
	private String productName;
	private String productType;
	private float price;
	private String specification;
	private int avgRating;
	private List<Review> reviews;

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public int getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(int avgRating) {
		this.avgRating = avgRating;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		return "ReviewByProductResponse [productId=" + productId + ", productName=" + productName + ", productType="
				+ productType + ", price=" + price + ", specification=" + specification + ", avgRating=" + avgRating
				+ ", reviews=" + reviews + "]";
	}

}
