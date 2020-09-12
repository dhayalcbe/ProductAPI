package com.zirius.productAPI.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {

	public Product(){

	}

	public Product(String name, String type, double price) {
		super();
		this.name = name;
		this.type = type;
		this.price = price;
	}

	@Id
	@GeneratedValue
	public Integer productId;
	public String name;
	public String type;
	public double price;
	public Integer reviewGroupId;


	public Integer getReviewGroupId() {
		return reviewGroupId;
	}

	public void setReviewGroupId(Integer reviewGroupId) {
		this.reviewGroupId = reviewGroupId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

}
