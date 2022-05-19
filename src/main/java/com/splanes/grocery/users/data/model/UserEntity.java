package com.splanes.grocery.users.data.model;

import com.splanes.grocery.users.data.model.common.Uuid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "user")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "internal_id")
    private Long internalId;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "uuid", nullable = false, unique = true))
    private Uuid uuid;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "alias")
    private String alias;

    @Column(name = "date_created")
    private String dateCreated;

    @Column(name = "date_last_login")
    private String dateLastUse;

    @OneToOne(mappedBy = "user", cascade = {CascadeType.ALL})
    private AuthEntity auth;

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
}
