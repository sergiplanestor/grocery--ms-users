package com.splanes.grocery.users.application.security.authentication

import com.splanes.grocery.users.application.HttpException
import com.splanes.grocery.users.application.security.JWTService
import com.splanes.grocery.users.domain.model.user.Role
import com.splanes.grocery.users.domain.model.user.fromNameOrElse
import com.splanes.grocery.users.domain.model.user.grantedAuthorityOf
import kotlinx.coroutines.reactor.mono
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.server.WebFilterExchange
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class JWTServerAuthenticationSuccessHandler(
    private val jwtService: JWTService,
    @Value("\${app.access-lifetime}") val accessLifetime: Int,
    @Value("\${app.refresh-lifetime}") val refreshLifetime: Int,
) : ServerAuthenticationSuccessHandler {

    private val FIFTEEN_MIN = 1000 * 60 * 15
    private val FOUR_HOURS = 1000 * 60 * 60 * 4

    override fun onAuthenticationSuccess(
        webFilterExchange: WebFilterExchange?,
        authentication: Authentication?
    ): Mono<Void> = mono {
        when (val principal = authentication?.principal ?: throw HttpException.unauthorized()) {
            is User -> {
                val roles = principal.authorities.map { Role.fromNameOrElse(it.authority) }
                val accessToken = jwtService.tokenOf(principal.username, accessLifetime, roles)
                val refreshToken = jwtService.refresh(principal.username, refreshLifetime, roles)
                webFilterExchange?.exchange?.response?.headers?.set("Authorization", accessToken)
                webFilterExchange?.exchange?.response?.headers?.set("JWT-Refresh-Token", refreshToken)
            }
        }

        return@mono null
    }
}