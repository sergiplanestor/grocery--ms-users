package com.splanes.grocery.users.domain.model.user;

import com.splanes.grocery.users.domain.security.model.UserAuthentication;
import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthenticationData implements UserAuthentication {
    private String email;
    private String password;
    private UserRole role;
}
