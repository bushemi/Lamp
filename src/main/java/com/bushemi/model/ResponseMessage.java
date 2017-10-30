package com.bushemi.model;


public class ResponseMessage {

	private String status;
	private PersonInfo personInfo;
	private String errorMessage;

	public static ResponseMessage okMessage(PersonInfo personInfo) {
		return new ResponseMessage("OK", personInfo);
	}

	public static ResponseMessage errorMessage(String message) {
		return new ResponseMessage("ERROR", message);
	}

	private ResponseMessage(String status, PersonInfo personStatus) {
		this.status = status;
		this.personInfo = personStatus;
	}

	private ResponseMessage(String status, String errorMessage) {
		this.status = status;
		this.errorMessage = errorMessage;
	}

	public String getStatus() {
		return status;
	}

	public PersonInfo getPersonInfo() {
		return personInfo;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
