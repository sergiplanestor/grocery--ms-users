package com.splanes.grocery.ms_customer.domain.model.customer

import org.springframework.security.core.GrantedAuthority
import java.util.Date
import javax.validation.constraints.NotBlank
import javax.validation.constraints.PastOrPresent

data class Customer(
    @field:NotBlank
    val uuid: String,

    @field:NotBlank
    val email: String,

    @field:NotBlank
    val alias: String,

    @field:NotBlank
    val password: String,

    val role: Role,

    @field:PastOrPresent
    @field:NotBlank
    val dateSignUp: Date,

    @field:PastOrPresent
    @field:NotBlank
    val dateLastSignIn: Date,
) : GrantedAuthority {

    override fun getAuthority(): String = role.toString()
}
