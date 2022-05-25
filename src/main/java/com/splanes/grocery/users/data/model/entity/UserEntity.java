package com.splanes.grocery.users.data.model.entity;

import com.splanes.grocery.users.data.model.common.DomainMapperEntity;
import com.splanes.grocery.users.data.model.common.Uuid;
import com.splanes.grocery.users.domain.model.user.User;
import com.splanes.grocery.users.domain.security.model.UserAuthentication;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "user")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends DomainMapperEntity<User> implements UserAuthentication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "internal_id")
    private Long internalId;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "uuid", nullable = false, unique = true))
    private Uuid uuid;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "alias", nullable = false)
    private String alias;

    @Column(name = "pwd", nullable = false)
    private String pwd;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "date_created", nullable = false)
    private String dateCreated;

    @Column(name = "date_last_login")
    private String dateLastUse;


    @Override
    public String getPassword() {
        return pwd;
    }

    @Override
    public String getUsername() {
        return alias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserEntity that = (UserEntity) o;
        return internalId != null && Objects.equals(internalId, that.internalId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


    @Override
    public User mapToDomain() {

        return User.builder()
                .id()
                .alias()
                .email()
                .dateCreated()
                .dateLastUse()
    }
}
