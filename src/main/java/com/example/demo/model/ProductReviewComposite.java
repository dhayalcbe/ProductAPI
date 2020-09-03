package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name ="productReviewComposite")
public class ProductReviewComposite implements Serializable {
	
	private Long  productID;
	@Id
    @Column(unique = true)
	private  Long  reviewId;
	
	
	ProductReviewComposite()
	{
		
	}
	
	public ProductReviewComposite(Long productID, Long reviewId) {
		this.productID = productID;
		this.reviewId = reviewId;
	}

	public Long getProductID() {
		return productID;
	}

	public Long getReviewId() {
		return reviewId;
	}
	
	
	
	
}
