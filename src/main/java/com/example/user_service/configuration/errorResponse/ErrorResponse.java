package com.example.user_service.configuration.errorResponse;

public class ErrorResponse {

    private Boolean status = false;
    private String message;
    private String timestamp;

    public ErrorResponse(String message, String timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}