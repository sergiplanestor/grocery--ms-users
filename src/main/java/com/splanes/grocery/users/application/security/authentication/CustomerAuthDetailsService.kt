package com.splanes.grocery.users.application.security.authentication

import com.splanes.grocery.users.domain.model.user.toSpringModel
import com.splanes.grocery.users.domain.repository.UserRepository
import kotlinx.coroutines.reactor.mono
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserAuthDetailsService(
    private val repository: UserRepository
) : ReactiveUserDetailsService {

    override fun findByUsername(username: String?): Mono<UserDetails> = mono {
        runCatching { repository.findByEmail(username!!)!!.toSpringModel() }.getOrElse { throw RuntimeException(it) }
    }
}