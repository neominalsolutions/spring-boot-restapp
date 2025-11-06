package com.mertalptekin.springbootrestapp.presentation.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

// Kimlik doğrulaması başarısız olduğunda çağrılan bileşen

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        response.setContentType("application/json");


        if(authException.getAuthenticationRequest().isAuthenticated()){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getOutputStream().println(
                    "{ \"error\": \"Forbidden\", " +
                            "\"message\": \"" + authException.getMessage() + "\" }"
            );

        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getOutputStream().println(
                    "{ \"error\": \"Unauthorized\", " +
                            "\"message\": \"" + authException.getMessage() + "\" }"
            );
        }


    }
}
