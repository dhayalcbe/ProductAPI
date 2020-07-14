package com.spring.productapi.dto;

public class ProductSuccessResponse {

	private long productId;
	private String productName;
	private String message;

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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "SuccessResponse [productId=" + productId + ", productName=" + productName + ", message=" + message
				+ "]";
	}

}
