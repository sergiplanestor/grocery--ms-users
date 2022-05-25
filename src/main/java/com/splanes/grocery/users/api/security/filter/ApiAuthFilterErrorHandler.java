package com.splanes.grocery.users.api.security.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j(topic = "AuthEntryPointHandler")
public class ApiAuthFilterErrorHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        String msg = "Incoming request URI: '" + request.getRequestURI() + "'. This URL is not allowed without authorization.";
        log.warn(msg);
        log.warn("Nested error message: ");
        log.warn(authException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, msg);
    }
}
