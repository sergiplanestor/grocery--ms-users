package com.splanes.grocery.users.data.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "token_log")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"owner", "jwt"}))
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "internal_id")
    private Long internalId;

    @Column(name = "owner", nullable = false)
    private String user;

    @Column(name = "jwt", nullable = false)
    private String token;

    @Column(name = "timestamp", nullable = false)
    private String timestamp;
}
