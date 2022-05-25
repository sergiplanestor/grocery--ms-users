package com.splanes.grocery.users.api.security.filter;

import com.splanes.grocery.users.domain.security.jwt.TokenJwtHelper;
import com.splanes.grocery.users.domain.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j(topic = "ApiAuthenticationFilter")
public class ApiAuthFilter extends AuthenticationFilter {

    public ApiAuthFilter(AuthenticationManager authenticationManager, TokenJwtHelper helper, UserService service) {
        super(authenticationManager, new ApiAuthConverter(helper, service));
    }

    public ApiAuthFilter(AuthenticationManagerResolver<HttpServletRequest> authenticationManagerResolver,
                         TokenJwtHelper helper, UserService service) {
        super(authenticationManagerResolver, new ApiAuthConverter(helper, service));
    }
}
