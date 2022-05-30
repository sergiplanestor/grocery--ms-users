package com.splanes.grocery.users.domain.model.user

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User as SpringUser

data class Customer(
    val uuid: String,
    val email: String,
    val alias: String,
    val password: String,
    val role: Role,
    val dateCreation: String,
    val dateLastUse: String,
) : GrantedAuthority {

    override fun getAuthority(): String = role.toString()
}

fun Customer.toSpringModel(): SpringUser =
    SpringUser(email, password, listOf(this))
