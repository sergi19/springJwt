package com.mycompany.dto;

public class ResponseDTO {

	private Object response;

	public ResponseDTO(Object response) {
		this.response = response;
	}

	public ResponseDTO() {
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}
	
}
