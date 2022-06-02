package com.splanes.grocery.ms_customer.application.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.Claim
import com.auth0.jwt.interfaces.DecodedJWT
import com.splanes.grocery.ms_customer.domain.model.customer.Role
import com.splanes.grocery.ms_customer.domain.model.customer.grantedAuthorityOf
import com.splanes.grocery.ms_customer.utils.date.add
import com.splanes.grocery.ms_customer.utils.date.now
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Service

@Service
class JWTService(
    @Value("\${app.issuer}") val issuer: String,
    @Value("\${app.access}") val secret: String,
    @Value("\${app.refresh}") val refresh: String,
) {

    fun tokenOf(username: String, expirationInMillis: Int, roles: List<Role>): String =
        generate(username, expirationInMillis, roles, secret)

    fun decode(token: String): DecodedJWT =
        decode(secret, token)

    fun refresh(username: String, expirationInMillis: Int, roles: List<Role>): String =
        generate(username, expirationInMillis, roles, refresh)

    fun decodeAndRefresh(refreshToken: String): DecodedJWT {
        return decode(refresh, refreshToken)
    }

    fun rolesOf(decodedJWT: DecodedJWT) = decodedJWT.getClaim(CLAIM_ROLES).mapToGrantedAuthority()

    private fun generate(
        customerName: String,
        expirationInMillis: Int,
        roles: List<Role>,
        signature: String
    ): String {
        val now = now()
        return JWT.create()
            .withSubject(customerName)
            .withIssuer(issuer)
            .withIssuedAt(now)
            .withExpiresAt(now.add(expirationInMillis))
            .withArrayClaim(CLAIM_ROLES, roles.map { it.value() }.toTypedArray())
            .sign(Algorithm.HMAC512(signature.toByteArray()))
    }

    private fun decode(signature: String, token: String): DecodedJWT {
        return JWT.require(Algorithm.HMAC512(signature.toByteArray()))
            .build()
            .verify(token.replace("Bearer ", ""))
    }

    private fun Claim.mapToGrantedAuthority(): List<GrantedAuthority> =
        asList(String::class.java).map { role -> Role.grantedAuthorityOf(role) }

    companion object {
        private const val CLAIM_ROLES = "roles"
    }
}