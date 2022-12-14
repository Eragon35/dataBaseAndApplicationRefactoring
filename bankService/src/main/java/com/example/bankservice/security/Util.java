package com.example.bankservice.security;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class Util {

    public static String getUserFromContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
