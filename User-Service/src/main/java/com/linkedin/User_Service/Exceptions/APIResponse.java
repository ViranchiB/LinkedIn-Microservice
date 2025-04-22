package com.linkedin.User_Service.Exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class APIResponse {
    private LocalDateTime timestamp;
    private String error;
    private HttpStatus status;
    private String path;

    public APIResponse(LocalDateTime timestamp, String error, HttpStatus status, String path) {
        this.timestamp = timestamp;
        this.error = error;
        this.status = status;
        this.path = path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
