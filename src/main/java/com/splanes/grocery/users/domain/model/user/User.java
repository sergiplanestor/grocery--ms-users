package com.splanes.grocery.users.domain.model.user;

import com.splanes.grocery.users.domain.model.common.DomainModel;
import com.splanes.grocery.users.domain.security.model.UserAuthentication;
import lombok.*;

import java.security.Principal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends DomainModel implements Principal {
    @NonNull
    private String id;

    @NonNull
    private String alias;

    private UserAuthentication authentication;

    @NonNull
    private String dateCreated;

    private String dateLastUse;

    @Override
    public String getName() {
        return getAlias();
    }
}
