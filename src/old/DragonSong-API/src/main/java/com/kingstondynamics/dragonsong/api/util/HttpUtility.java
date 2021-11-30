package com.kingstondynamics.dragonsong.api.util;

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
