package com.splanes.grocery.users.api.model.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpRequest {

    @JsonAlias(value = "alias")
    @NotNull
    private String alias;

    @JsonAlias(value = "email")
    @NotNull
    private String email;

    @JsonAlias(value = "pwd")
    @NotNull
    private String password;
}
