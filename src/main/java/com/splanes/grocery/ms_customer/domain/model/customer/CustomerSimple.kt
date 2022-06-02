package com.splanes.grocery.ms_customer.domain.model.customer

import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.PastOrPresent

data class CustomerSimple(
    @field:NotBlank
    val uuid: String,

    @field:NotBlank
    val email: String,

    @field:NotBlank
    val alias: String,

    @field:PastOrPresent
    @field:NotBlank
    val dateSignUp: Date,

    @field:PastOrPresent
    @field:NotBlank
    val dateLastSignIn: Date,
)
