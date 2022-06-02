package com.splanes.grocery.ms_customer.application.api.v1.auth.model

import com.fasterxml.jackson.annotation.JsonAlias
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class SignUpRequest(
    @field:JsonAlias("email")
    @field:Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")
    @field:NotBlank
    @field:NotNull
    val email: String,

    @field:JsonAlias("user_alias")
    @field:Size(min = 3, max = 50)
    @field:NotBlank
    @field:NotNull
    val alias: String,

    @field:JsonAlias("pwd")
    @field:Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}\$")
    @field:NotBlank
    @field:NotNull
    val password: String
)
