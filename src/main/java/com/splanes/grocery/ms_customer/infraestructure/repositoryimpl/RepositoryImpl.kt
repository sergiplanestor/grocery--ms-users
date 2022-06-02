package com.splanes.grocery.ms_customer.infraestructure.repositoryimpl

import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import reactor.core.publisher.Mono

abstract class RepositoryImpl {

    protected suspend fun <T, R> Mono<T>.then(then: T.() -> R): R =
        awaitSingle().run(then)

    protected suspend fun <T, R> Mono<T>.thenOrNull(then: T?.() -> R): R =
        awaitSingleOrNull().run(then)

}