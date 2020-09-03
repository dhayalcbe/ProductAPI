package com.example.demo.exception.model;

import java.util.Date;

public class ExceptionResponse {

private Date timeStamp;
private String messages;
private String details;
public ExceptionResponse(Date timeStamp, String messages, String details) {
	super();
	this.timeStamp = timeStamp;
	this.messages = messages;
	this.details = details;
}
public Date getTimeStamp() {
	return timeStamp;
}
public String getMessages() {
	return messages;
}
public String getDetails() {
	return details;
}


}
