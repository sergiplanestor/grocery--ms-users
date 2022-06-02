package com.splanes.grocery.ms_customer.application.api.v1.auth.model

import com.fasterxml.jackson.annotation.JsonAlias
import com.splanes.grocery.ms_customer.utils.validator.PATTERN_EMAIL
import com.splanes.grocery.ms_customer.utils.validator.PATTERN_PASSWORD
import javax.validation.constraints.*

data class SignInRequest(
    @field:JsonAlias("email")
    @field:Email(regexp = PATTERN_EMAIL)
    @field:NotBlank
    @field:NotNull
    val email: String,

    @field:JsonAlias("pwd")
    @field:Pattern(regexp = PATTERN_PASSWORD)
    @field:NotBlank
    @field:NotNull
    val password: String
)