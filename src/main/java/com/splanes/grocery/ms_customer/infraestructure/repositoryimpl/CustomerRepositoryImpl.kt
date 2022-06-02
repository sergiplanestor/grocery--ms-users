package com.splanes.grocery.ms_customer.infraestructure.repositoryimpl

import com.splanes.grocery.ms_customer.domain.model.customer.Customer
import com.splanes.grocery.ms_customer.domain.repository.CustomerRepository
import com.splanes.grocery.ms_customer.infraestructure.db.repository.CustomerDataRepository
import com.splanes.grocery.ms_customer.infraestructure.mapper.CustomerMapper
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.stereotype.Repository

@Repository
class CustomerRepositoryImpl(
    private val jpa: CustomerDataRepository,
    private val mapper: CustomerMapper
) : CustomerRepository, RepositoryImpl() {

    override suspend fun persist(customer: Customer): Customer {
        val entity = customer.let(mapper::toEntity)
        return jpa.save(entity).then(mapper::toCustomer)
    }

    override suspend fun all(): List<Customer> =
        jpa.findAll()
            .map(mapper::toCustomer)
            .collectList()
            .awaitSingleOrNull()
            .orEmpty()

    override suspend fun findByUuid(uuid: String): Customer? =
        jpa.findByParam("uuid", uuid).thenOrNull { this?.let(mapper::toCustomer) }
    // jpa.findByUuid(uuid).thenOrNull { this?.let(mapper::toDomain) }

    override suspend fun findByAlias(alias: String): Customer? =
        jpa.findByParam("alias", alias).thenOrNull { this?.let(mapper::toCustomer) }
    // jpa.findByAlias(alias).thenOrNull { this?.let(mapper::toDomain) }

    override suspend fun findByEmail(email: String): Customer? =
        jpa.findByParam("email", email).thenOrNull { this?.let(mapper::toCustomer) }
    // jpa.findByEmail(email).thenOrNull { this?.let(mapper::toDomain) }

}