package com.spring.productapi.dto;

public class Review {
	
	private long id;
	//private ReviewGroup group;
	private String comments;
	private int ratings;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/*public ReviewGroup getGroup() {
		return group;
	}

	public void setGroup(ReviewGroup group) {
		this.group = group;
	}*/

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
		return "Review [id=" + id + ", comments=" + comments + ", ratings=" + ratings + "]";
	}

	
}
