package com.splanes.grocery.ms_customer.application.security.authentication

import com.splanes.grocery.ms_customer.domain.model.customer.Customer
import com.splanes.grocery.ms_customer.domain.repository.CustomerRepository
import com.splanes.grocery.ms_customer.utils.getOrThrow
import com.splanes.grocery.ms_customer.utils.scope.getOrThrow
import kotlinx.coroutines.reactor.mono
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class CustomerAuthDetailsService(
    private val repository: CustomerRepository
) : ReactiveUserDetailsService {

    private suspend fun findByEmail(email: String): UserDetails = runCatching {
        repository.findByEmail(email)?.toUserDetails().getOrThrow(badCredentialsExceptionOf(email))
    }.getOrThrow { err -> RuntimeException(err) }

    override fun findByUsername(username: String?): Mono<UserDetails> = mono {
        findByEmail(username.orEmpty())
    }

    private fun badCredentialsExceptionOf(email: String) =
        BadCredentialsException("The email provided=`$email` does not belongs to any existing customer.")

    private fun Customer.toUserDetails(): User = User(email, password, listOf(this))
}