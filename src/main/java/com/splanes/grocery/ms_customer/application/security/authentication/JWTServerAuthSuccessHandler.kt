package com.splanes.grocery.ms_customer.application.security.authentication

import com.splanes.grocery.ms_customer.application.HttpException
import com.splanes.grocery.ms_customer.application.security.JWTService
import com.splanes.grocery.ms_customer.domain.model.customer.Role
import kotlinx.coroutines.reactor.mono
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.server.WebFilterExchange
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class JWTServerAuthSuccessHandler(
    private val jwtService: JWTService,
    @Value("\${app.access-lifetime}") val accessLifetime: String,
    @Value("\${app.refresh-lifetime}") val refreshLifetime: String,
) : ServerAuthenticationSuccessHandler {

    override fun onAuthenticationSuccess(
        webFilterExchange: WebFilterExchange?,
        authentication: Authentication?
    ): Mono<Void> = mono {
        when (val principal = authentication?.principal ?: throw HttpException.unauthorized()) {
            is User -> {
                val roles = principal.authorities.map { Role.parseOrDefault(it.authority) }
                val accessToken = jwtService.tokenOf(principal.username, accessLifetime.toInt(), roles)
                val refreshToken = jwtService.refresh(principal.username, refreshLifetime.toInt(), roles)
                webFilterExchange?.exchange?.response?.headers?.set("Authorization", accessToken)
                webFilterExchange?.exchange?.response?.headers?.set("JWT-Refresh-Token", refreshToken)
            }
        }

        return@mono null
    }
}