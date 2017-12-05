package com.bushemi.model;


public class ResponseMessage {

	private String status;
	private Object object;
	private String errorMessage;

	public static ResponseMessage okMessage(Object personInfo) {
		return new ResponseMessage("OK", personInfo);
	}

	public static ResponseMessage errorMessage(String message) {
		return new ResponseMessage("ERROR", message);
	}

	private ResponseMessage(String status, Object personStatus) {
		this.status = status;
		this.object = personStatus;
	}

	private ResponseMessage(String status, String errorMessage) {
		this.status = status;
		this.errorMessage = errorMessage;
	}

	public String getStatus() {
		return status;
	}

	public Object getObject() {
		return object;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("ResponseMessage{");
		sb.append("status='").append(status).append('\'');
		sb.append(", object=").append(object);
		sb.append(", errorMessage='").append(errorMessage).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
