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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class ResponseFactory {
    
    // Spring Components
    private HttpServletRequest request;
    
    @Autowired
    public ResponseFactory(HttpServletRequest request) {
        this.request = request;
    }
    
    /**
     * Generate Response
     *
     * @param <T>
     * @param status
     * @param message
     * @return
     */
    public <T> ResponseEntity<?> buildResponse(HttpStatus status, T message) {
        GenericResponse<T> genericResponse = new GenericResponse<>("1.0.0", status.value(), message, request.getRequestURI());
        return ResponseEntity.status(status).body(genericResponse);
    }
    
    /**
     * Generate Response
     *
     * @param <T>
     * @param status
     * @param message
     * @param URI
     * @return
     */
    public <T> ResponseEntity<?> buildResponse(HttpStatus status, T message, String URI) {
        GenericResponse<T> genericResponse = new GenericResponse<>("1.0.0", status.value(), message, URI);
        return ResponseEntity.status(status).body(genericResponse);
    }
    
    /**
     * Generate Response With Custom Headers
     *
     * @param <T>
     * @param status
     * @param headers
     * @param message
     * @param URI
     * @return
     */
    public <T> ResponseEntity<?> buildResponse(HttpStatus status, HttpHeaders headers, T message, String URI) {
        GenericResponse<T> genericResponse = new GenericResponse<>("1.0.0", status.value(), message, URI);
        return ResponseEntity.status(status).headers(headers).body(genericResponse);
    }
    
    /**
     * Generate Error Response
     *
     * @param <T>
     * @param status
     * @param error
     * @param message
     * @param URI
     * @return
     */
    public <T> ResponseEntity<?> buildResponse(HttpStatus status, String error, T message, String URI) {
        GenericResponse<T> genericResponse = new GenericResponse<>("1.0.0", status.value(), error, message, URI);
        return ResponseEntity.status(status).body(genericResponse);
    }
    
    /**
     * Generate Error Response With Custom Headers
     *
     * @param <T>
     * @param status
     * @param headers
     * @param error
     * @param message
     * @param URI
     * @return
     */
    public <T> ResponseEntity<?> buildResponse(HttpStatus status, HttpHeaders headers, String error, T message, String URI) {
        GenericResponse<T> genericResponse = new GenericResponse<>("1.0.0", status.value(), error, message, URI);
        return ResponseEntity.status(status).headers(headers).body(genericResponse);
    }

//    /**
//     * Helper Function. Gets associated error message if one exists
//     * @param code
//     * @return
//     */
//    private static String getMessageDescription(String code, UserMessageRepository userMessageRepository) {
//
//        Optional<UserMessageModel> oMessage = userMessageRepository.findLatestUserMessageByCode(code);
//
//        if (oMessage.isPresent()) {
//            return oMessage.get().getUserMessageDescription();
//        }
//
//        return UserMessage.DEFAULT_MESSAGE_DESCRIPTION;
//    }
//
//    /**
//     * Helper Function.Builds Response Entities
//     * @param request
//     * @param status
//     * @param code
//     * @param repository
//     * @return
//     */
//    public static ResponseEntity<?> buildResponseWithMessage(HttpServletRequest request, HttpStatus status, UserMessage code, UserMessageRepository repository) {
//        return ResponseFactory.buildResponse(status, code.toString(), getMessageDescription(code.toString(), repository), request.getRequestURI());
//    }
//
//    /**
//     * Helper Function.Builds Response Entities
//     * @param request
//     * @param status
//     * @param code
//     * @param repository
//     * @return
//     */
//    public static ResponseEntity<?> buildResponseWithMessage(HttpServletRequest request, HttpStatus status, String code, UserMessageRepository repository) {
//        return ResponseFactory.buildResponse(status, code, getMessageDescription(code, repository), request.getRequestURI());
//    }
//
//    /**
//     * Helper Function.Builds Response Entities
//     * @param request
//     * @param status
//     * @param code
//     * @param repository
//     * @param substitution
//     * @return
//     */
//    public static ResponseEntity<?> buildResponseWithMessage(HttpServletRequest request, HttpStatus status, String code, UserMessageRepository repository, String substitution) {
//        String message = DataHelperUtil.substitute(getMessageDescription(code, repository), substitution);
//        return ResponseFactory.buildResponse(status, code, message, request.getRequestURI());
//    }
//
//    /**
//     * Helper Function.Builds Response Entities
//     * @param request
//     * @param status
//     * @param headers
//     * @param code
//     * @param repository
//     * @return
//     */
//    public static ResponseEntity<?> buildResponseWithMessage(HttpServletRequest request, HttpStatus status, HttpHeaders headers, UserMessage code, UserMessageRepository repository) {
//        return ResponseFactory.buildResponse(status, headers, code.toString(), getMessageDescription(code.toString(), repository), request.getRequestURI());
//    }
//
//    /**
//     * Helper Function.Builds Response Entities
//     * @param request
//     * @param status
//     * @param code
//     * @param object
//     * @return
//     */
//    public static ResponseEntity<?> buildResponseWithObject(HttpServletRequest request, HttpStatus status, UserMessage code, Object object) {
//        return ResponseFactory.buildResponse(status, code.toString(), object, request.getRequestURI());
//    }
//
//    /**
//     * Helper Function.Builds Response Entities
//     * @param request
//     * @param status
//     * @param headers
//     * @param code
//     * @param object
//     * @return
//     */
//    public static ResponseEntity<?> buildResponseWithObject(HttpServletRequest request, HttpStatus status, HttpHeaders headers, UserMessage code, Object object) {
//        return ResponseFactory.buildResponse(status, headers, code.toString(), object, request.getRequestURI());
//    }
}
