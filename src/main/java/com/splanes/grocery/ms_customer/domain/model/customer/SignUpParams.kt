package com.splanes.grocery.ms_customer.domain.model.customer

data class SignUpParams(
    val alias: String,
    val email: String,
    val password: String,
    val autoSignIn: Boolean
)
