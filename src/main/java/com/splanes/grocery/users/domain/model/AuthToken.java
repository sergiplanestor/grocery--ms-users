package com.splanes.grocery.users.domain.model;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthToken {
    private String email;
    private Jws<Claims> jws;
}
