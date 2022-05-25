package com.splanes.grocery.users.domain.service.impl;

import com.splanes.grocery.users.data.repository.UserDataRepository;
import com.splanes.grocery.users.domain.model.user.User;
import com.splanes.grocery.users.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j(topic = "UserService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UserServiceImpl implements UserService {

    private UserDataRepository repository;

    @Override
    public List<User> all() {
        return null;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByAlias(String alias) {
        return Optional.empty();
    }

    @Override
    public boolean signUp(User user) {
        return false;
    }

    @Override
    public boolean signIn(User user) {
        return false;
    }

    @Override
    public boolean signOut(User user) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }
}
