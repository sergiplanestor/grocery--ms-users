package com.splanes.grocery.users.application.api.auth.model

import com.fasterxml.jackson.annotation.JsonAlias
import javax.validation.constraints.*

sealed class SignInRequest(val identifier: String, open val password: String) {
    data class Email(
        @field:JsonAlias("email")
        @field:javax.validation.constraints.Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")
        @field:NotBlank
        @field:NotNull
        val email: String,

        @field:JsonAlias("pwd")
        @field:Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}\$")
        @field:NotBlank
        @field:NotNull
        override val password: String
    ): SignInRequest(email, password)
    data class Alias(
        @field:JsonAlias("user_alias")
        @field:Size(min = 3, max = 50)
        @field:NotBlank
        @field:NotNull
        val alias: String,

        @field:JsonAlias("pwd")
        @field:Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}\$")
        @field:NotBlank
        @field:NotNull
        override val password: String
    ): SignInRequest(alias, password)
}
