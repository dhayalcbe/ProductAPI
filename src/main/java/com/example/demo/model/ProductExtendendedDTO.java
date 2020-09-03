package com.example.demo.model;

import java.util.List;

public class ProductExtendendedDTO  {
	
	private 	 Product product ;

	private List<Review> review;

	public List<Review> getReview() {
		return review;
	}

	public void setReview(List<Review> review) {
		this.review = review;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}


	
	
	
	

}
