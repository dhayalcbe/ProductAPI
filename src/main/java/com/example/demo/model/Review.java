package com.example.demo.model;

public class Review   {
	
	private Long  id;
	private  String comments;
	private Integer starRating;
	private ReviewGroup reviewGroup;
	
	public Long  getId() {
		return id;
	}
	public void setId(Long  id) {
		this.id = id;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Integer getStarRating() {
		return starRating;
	}
	public void setStarRating(Integer starRating) {
		this.starRating = starRating;
	}
	public ReviewGroup getReviewGroup() {
		return reviewGroup;
	}
	public void setReviewGroup(ReviewGroup reviewGroup) {
		this.reviewGroup = reviewGroup;
	}
	
	
	
	
}
