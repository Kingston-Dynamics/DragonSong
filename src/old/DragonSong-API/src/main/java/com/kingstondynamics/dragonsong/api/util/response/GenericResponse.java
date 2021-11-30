package com.kingstondynamics.dragonsong.api.util.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.OffsetDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponse<T> {
    
    private String version;
    private int status;
    private String error;
    private T message;
    private String path;
    private String timestamp;
    
    /**
     * Generate API Response
     *
     * @param version Application Version
     * @param status  HTTP Status Value
     * @param message Anything you Want
     * @param path    Request Path
     */
    protected GenericResponse(String version, int status, T message, String path) {
        this.version = version;
        this.status = status;
        this.message = message;
        this.path = path;
        this.timestamp = OffsetDateTime.now().toString();
    }
    
    /**
     * Generate API Response
     *
     * @param version
     * @param status
     * @param error
     * @param message
     * @param path
     */
    protected GenericResponse(String version, int status, String error, T message, String path) {
        this.version = version;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.timestamp = OffsetDateTime.now().toString();
    }
    
    public String getVersion() {
        return version;
    }
    
    public String getTimestamp() {
        return timestamp;
    }
    
    public int getStatus() {
        return status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
    
    public String getError() {
        return error;
    }
    
    public void setError(String error) {
        this.error = error;
    }
    
    public T getMessage() {
        return message;
    }
    
    public void setMessage(T message) {
        this.message = message;
    }
    
    public String getPath() {
        return path;
    }
    
    public void setPath(String path) {
        this.path = path;
    }
}
