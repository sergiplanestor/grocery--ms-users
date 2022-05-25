package com.splanes.grocery.users.domain.model.user;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
public enum UserRole {
    Sudo("sudo"),
    System("system"),
    Client("client"),
    Anonymous("anonymous"),;

    public final String name;

    UserRole(String name) {
        this.name = name;
    }

    public GrantedAuthority authority() {
        return new SimpleGrantedAuthority(name);
    }
}
