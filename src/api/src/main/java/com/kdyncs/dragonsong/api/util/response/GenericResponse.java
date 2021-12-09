/*
 * Copyright (C) 2021 Kingston Dynamics Inc.
 *
 * This file is part of DragonSong
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.kdyncs.dragonsong.api.util.response;

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
