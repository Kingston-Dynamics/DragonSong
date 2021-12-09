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

package com.kdyncs.dragonsong.api.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Component
public class HttpUtility {
    
    private HttpServletRequest http;
    
    @Autowired
    public HttpUtility(HttpServletRequest http) {
        this.http = http;
    }
    
    public String getHeader(String name) {
        return http.getHeader(name);
    }
    
    public String getCookie(String name) {
        
        // Extract Cookies
        Cookie[] cookies = http.getCookies();
        
        // Verify Array Contents
        if (cookies == null || cookies.length == 0) {
            return null;
        }
        
        // Extract Cookie
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie.getValue();
            }
        }
        
        return null;
    }
}
