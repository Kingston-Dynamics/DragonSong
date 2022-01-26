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

package com.kdyncs.dragonsong.api.web.controller;

import com.kdyncs.dragonsong.api.util.response.ResponseFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;


@RestController
@RequestMapping("/health")
public class HealthController {
    
    // Spring Components
    private final ResponseFactory response;
    
    // Logging
    private final Logger log = LogManager.getLogger();
    
    @Autowired
    public HealthController(ResponseFactory response) {
        this.response = response;
    }
    
    @PostConstruct
    public void init() {
        log.info("Loading {}", this.getClass().getSimpleName());
    }

    @RequestMapping("/")
    public ResponseEntity<?> health() {
        log.trace("Checking Health.");
        return response.buildResponse(HttpStatus.OK, "Application Running");
    }
}
