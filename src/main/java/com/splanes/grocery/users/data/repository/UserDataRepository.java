package com.splanes.grocery.users.data.repository;

import com.splanes.grocery.users.data.model.entity.UserEntity;
import com.splanes.grocery.users.data.repository.common.DataRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepository extends DataRepository<UserEntity> {

    @Query("SELECT u FROM user u WHERE u.email=:email")
    UserEntity findByEmail(@Param("email") String email);
}
