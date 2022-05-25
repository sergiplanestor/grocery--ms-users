package com.splanes.grocery.users.domain.security.model;

import com.splanes.grocery.users.domain.model.user.UserRole;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GroceryAuthorities {

    public static List<GrantedAuthority> authoritiesOf(UserRole... roles) {
        return Arrays.stream(roles).map(UserRole::authority).collect(Collectors.toList());
    }
}
