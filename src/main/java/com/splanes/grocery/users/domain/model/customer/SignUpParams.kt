package com.splanes.grocery.users.domain.model.user

data class SignUpParams(
    val alias: String,
    val email: String,
    val password: String,
    val autoSignIn: Boolean
)
