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

package com.kdyncs.dragonsong.api.config;

import com.kdyncs.dragonsong.api.util.response.ResponseFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    // Logging
    private static final Logger log = LogManager.getLogger();
    
    // Spring Components
    private final HttpServletRequest http;
    private final ResponseFactory response;
    
    public GlobalExceptionHandler(HttpServletRequest http, ResponseFactory responseFactory) {
        this.http = http;
        this.response = responseFactory;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public final ResponseEntity<?> NotFoundException(NoHandlerFoundException ex) {
        log.trace("Endpoint Not Defined", ex);
        return response.buildResponse(HttpStatus.NOT_FOUND, "NOT_FOUND", null, http.getRequestURI());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<?> handleNotReadableException(HttpMessageNotReadableException ex) {
        return response.buildResponse(HttpStatus.BAD_REQUEST, "BAD_REQUEST", "Malformed Request Body", http.getRequestURI());
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public final ResponseEntity<?> handleNotFoundException(MissingRequestHeaderException ex) {
        return response.buildResponse(HttpStatus.BAD_REQUEST, "BAD_REQUEST", "Missing Request Headers", http.getRequestURI());
    }
    
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<?> handleException(Exception ex) {
        log.error("Internal Server Error", ex);
        return response.buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", null, http.getRequestURI());
    }
}
