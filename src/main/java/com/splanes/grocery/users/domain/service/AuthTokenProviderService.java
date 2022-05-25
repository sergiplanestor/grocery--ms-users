package com.splanes.grocery.users.domain.service;

import com.splanes.grocery.users.domain.model.user.User;

public interface AuthTokenProviderService {
    String generate(User user);
    boolean validate(String token);
    String getNameByToken(String token);
}
