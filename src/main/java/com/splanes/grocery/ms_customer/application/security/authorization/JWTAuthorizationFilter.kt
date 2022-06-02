package com.splanes.grocery.ms_customer.application.security.authorization

import com.splanes.grocery.ms_customer.application.security.JWTService
import com.splanes.grocery.ms_customer.utils.loggerOf
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono
import reactor.util.context.Context


class JWTAuthorizationFilter(private val jwtService: JWTService) : WebFilter {

    private val logger = loggerOf()

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val authHeader = exchange.requestAuthHeader()
            .takeIf { auth -> !auth.isNullOrBlank() && auth.startsWith("bearer", ignoreCase = true) }
            ?: return chain.filter(exchange)

        val context = runCatching {
            val token = jwtService.decode(authHeader)
            val auth = UsernamePasswordAuthenticationToken(token.subject, null, jwtService.rolesOf(token))
            ReactiveSecurityContextHolder.withAuthentication(auth)
        }.getOrElse { exception ->
            logger.error("JWT error", exception)
            null
        }

        return chain.filter(exchange).withContext(context)
    }

    private fun ServerWebExchange.requestAuthHeader(): String? =
        this.request.headers.getFirst(HttpHeaders.AUTHORIZATION)

    private fun <T> Mono<T>.withContext(context: Context?): Mono<T> =
        if (context != null) {
            contextWrite(context)
        } else {
            contextWrite(ReactiveSecurityContextHolder.clearContext())
        }
}