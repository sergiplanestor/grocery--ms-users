package com.splanes.grocery.ms_customer.application.security.authentication

import com.splanes.grocery.ms_customer.application.HttpException
import com.splanes.grocery.ms_customer.application.api.v1.auth.model.SignInRequest
import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.coroutines.reactor.mono
import org.springframework.core.ResolvableType
import org.springframework.http.MediaType
import org.springframework.http.codec.json.AbstractJackson2Decoder
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import javax.validation.Validator

@Component
class JWTConverter(
    private val jacksonDecoder: AbstractJackson2Decoder,
    private val validator: Validator
) : ServerAuthenticationConverter {

    override fun convert(exchange: ServerWebExchange?): Mono<Authentication> = mono {
        with(exchange.toSignInRequest() ?: throw HttpException.badRequest()) {
            if (validator.validate(this).isNotEmpty()) {
                throw HttpException.badRequest()
            }
            UsernamePasswordAuthenticationToken(email, password)
        }
    }

    private suspend fun ServerWebExchange?.toSignInRequest(): SignInRequest? =
        this?.run {
            val dataBuffer = request.body
            val type = ResolvableType.forClass(SignInRequest::class.java)
            jacksonDecoder
                .decodeToMono(dataBuffer, type, MediaType.APPLICATION_JSON, mapOf())
                .onErrorResume { Mono.empty<SignInRequest>() }
                .cast(SignInRequest::class.java)
                .awaitFirstOrNull()
        }
}