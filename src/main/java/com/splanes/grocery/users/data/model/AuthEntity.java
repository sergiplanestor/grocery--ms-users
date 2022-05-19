package com.splanes.grocery.users.data.model;

import lombok.*;
import org.hibernate.annotations.Parent;

import javax.persistence.*;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthEntity {

    @OneToOne
    @JoinColumn(name = "user_internal_id", nullable = false)
    @Parent
    private UserEntity user;

    @Column(name = "password", nullable = false)
    private String password;
}
