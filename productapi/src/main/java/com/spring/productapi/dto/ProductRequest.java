package com.spring.productapi.dto;

public class ProductRequest {

	private String productName;
	private String productType;
	private float price;
	private String specification;

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

	@Override
	public String toString() {
		return "ProductRequest [productName=" + productName + ", productType=" + productType + ", price=" + price
				+ ", specification=" + specification + "]";
	}

}
