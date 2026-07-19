package com.example.crudSpringBoot.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class ValidationExecptionResponseDto {

    private LocalDateTime timestamp;
    private int statusCode;
    private String Error;
    private String message;
    private String path;
    private Map<String,String> fielderrors;

    public ValidationExecptionResponseDto(LocalDateTime timestamp, int statusCode, String error, String message, String path, Map<String, String> fielderrors) {
        this.timestamp = timestamp;
        this.statusCode = statusCode;
        Error = error;
        this.message = message;
        this.path = path;
        this.fielderrors = fielderrors;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getFielderrors() {
        return fielderrors;
    }

    public void setFielderrors(Map<String, String> fielderrors) {
        this.fielderrors = fielderrors;
    }
}
