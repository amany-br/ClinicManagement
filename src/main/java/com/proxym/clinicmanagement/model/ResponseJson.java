package com.proxym.clinicmanagement.model;

public class ResponseJson {

        boolean success;
        String message;
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public ResponseJson() {
        super();
    }
	public ResponseJson(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}
    
    
}
