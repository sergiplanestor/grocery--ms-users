package com.splanes.grocery.users.infraestructure.db.repository

import com.splanes.grocery.users.infraestructure.db.entity.CustomerEntity
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.r2dbc.repository.R2dbcRepository
import reactor.core.publisher.Mono

interface CustomerJpaRepository : R2dbcRepository<CustomerEntity, Long> {

    @Query("SELECT c FROM customer c WHERE c.?1 = ?2")
    fun findByParam(param: String, value: String): Mono<CustomerEntity?>

    @Query("SELECT c FROM customer c WHERE c.uuid = ?1")
    fun findByUuid(uuid: String): Mono<CustomerEntity?>

    @Query("SELECT c FROM customer c WHERE c.email = ?1")
    fun findByEmail(email: String): Mono<CustomerEntity?>

    @Query("SELECT c FROM customer c WHERE c.alias = ?1")
    fun findByAlias(alias: String): Mono<CustomerEntity?>
}