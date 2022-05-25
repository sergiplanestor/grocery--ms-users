package com.splanes.grocery.users.api.security.filter;

import com.splanes.grocery.users.domain.model.AuthToken;
import com.splanes.grocery.users.domain.security.InternalServerAuthToken;
import com.splanes.grocery.users.domain.security.helper.AuthHelper;
import com.splanes.grocery.users.domain.security.jwt.TokenJwtHelper;
import com.splanes.grocery.users.domain.security.model.UserAuthentication;
import com.splanes.grocery.users.domain.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;

@Slf4j(topic = "ApiAuthenticationFilter")
public class ApiAuthConverter implements AuthenticationConverter {

    private TokenJwtHelper helper;
    private UserService service;

    private static final String[] WHITELIST = {"^/auth.*$", "^/(swagger-ui)|(v3/api-docs).*$"};

    public ApiAuthConverter(TokenJwtHelper helper, UserService service) {
        this.helper = helper;
        this.service = service;
    }

    @Override
    public Authentication convert(HttpServletRequest request) {
        Authentication authentication = null;
        if (isMilky(request)) {
            log.info(">> Milky request - Internal Server authentication set.");
            authentication = InternalServerAuthToken.build();
        } else {
            try {
                log.info(">> Checking request's JWT");
                String email = TokenJwtHelper
                        .parse(helper.findByRequest(request)).orElseThrow()
                        .getEmail();
                UserAuthentication userAuthentication = service.findAuthenticationByEmail(email).orElseThrow();
                authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
                log.info(">> JWT token is valid");
            } catch (Exception e) {
                log.info(">> JWT token is NOT valid");
            }
        }
        return authentication;
    }

    private boolean isMilky(@NotNull HttpServletRequest request) {
        return Arrays.stream(WHITELIST)
                .allMatch(regex -> Pattern.matches(regex, request.getRequestURI()));
    }
}
