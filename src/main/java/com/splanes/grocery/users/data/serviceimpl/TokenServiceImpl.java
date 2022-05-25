package com.splanes.grocery.users.data.serviceimpl;

import com.splanes.grocery.users.data.repository.TokenDataRepository;
import com.splanes.grocery.users.domain.model.user.User;
import com.splanes.grocery.users.domain.security.jwt.TokenJwtHelper;
import com.splanes.grocery.users.domain.security.model.UserAuthentication;
import com.splanes.grocery.users.domain.service.AuthTokenProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements AuthTokenProviderService {

    private static final String ISSUER = "GroceryApp";
    private static final String ROLES = "Roles";

    @Autowired
    private TokenDataRepository repository;

    @Autowired
    private TokenJwtHelper jwtHelper;

    @Override
    public String generate(User user) {
        return jwtHelper.newJwt(user.getId(), user.getAuthentication().getEmail(), user.getAuthentication().getRolesAuthority());
    }

    @Override
    public boolean validate(String token) {
        return false;
    }

    @Override
    public String getNameByToken(String token) {
        return null;
    }
}
