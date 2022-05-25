package com.splanes.grocery.users.api.model.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.splanes.grocery.users.domain.security.jwt.TokenJwtHelper;
import lombok.*;

@EqualsAndHashCode
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    @JsonAlias(value = "jwt")
    private String jwt;
    @JsonAlias(value = "tokenType")
    private String type;

    public AuthResponse(String jwt) {
        this.jwt = jwt;
        this.type = TokenJwtHelper.BEARER_TYPE;
    }
}
