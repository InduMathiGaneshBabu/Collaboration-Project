package com.collaboration.models;

public class ErrorClazz 
{
private int errorcode;
private String errorMessage;
public int getErrorcode() {
	return errorcode;
}
public void setErrorcode(int errorcode) {
	this.errorcode = errorcode;
}
public String getErrorMessage() {
	return errorMessage;
}
public void setErrorMessage(String errorMessage) {
	this.errorMessage = errorMessage;
}
public ErrorClazz(int errorcode,String errorMessage)
{
	super();
	this.errorcode=errorcode;
	this.errorMessage=errorMessage;
	
}
}
