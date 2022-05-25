package com.splanes.grocery.users.domain.service;

import com.splanes.grocery.users.domain.model.user.User;
import com.splanes.grocery.users.domain.security.model.UserAuthentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    // GET
    List<User> all();

    Optional<User> findByEmail(String email);

    Optional<User> findByAlias(String alias);

    default Optional<UserAuthentication> findAuthenticationByEmail(String email) {
        return findByEmail(email).map(User::getAuthentication);
    }

    // POST
    boolean signUp(User user);

    boolean signIn(User user);

    boolean signOut(User user);

    // DELETE
    boolean delete(User user);

    @Override
    default UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return findAuthenticationByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with email: " + email));
    }
}
