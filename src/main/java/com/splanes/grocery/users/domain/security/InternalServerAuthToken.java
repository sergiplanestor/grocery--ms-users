package com.splanes.grocery.users.domain.security;

import com.splanes.grocery.users.domain.model.user.UserRole;
import com.splanes.grocery.users.domain.security.model.GroceryAuthorities;
import com.splanes.grocery.users.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class InternalServerAuthToken extends AbstractAuthenticationToken {

    @Autowired
    UserService service;

    @Value("${auth.internal.credentials}")
    private String credentials;

    private String uuid;

    public static InternalServerAuthToken build() {
        return new InternalServerAuthToken();
    }

    private InternalServerAuthToken() {
        super(GroceryAuthorities.authoritiesOf(UserRole.System));
        this.uuid = UUID.randomUUID().toString();
    }

    @Override
    public Object getCredentials() {
        return credentials + uuid;
    }

    @Override
    public Object getPrincipal() {
        return this.getClass().getSimpleName();
    }
}
