package com.splanes.grocery.ms_customer.domain.service.impl

import com.splanes.grocery.ms_customer.domain.model.customer.Customer
import com.splanes.grocery.ms_customer.domain.model.customer.CustomerMatcher
import com.splanes.grocery.ms_customer.domain.repository.CustomerRepository
import com.splanes.grocery.ms_customer.domain.service.CustomerService
import com.splanes.grocery.ms_customer.utils.validator.validateOrThrow
import org.springframework.stereotype.Service
import javax.validation.Validator

@Service
class CustomerServiceImpl(
    private val validator: Validator,
    private val repository: CustomerRepository
) : CustomerService {

    override suspend fun persist(customer: Customer): Customer =
        with(customer.validateOrThrow(validator)) {
            repository.persist(customer = this)
        }

    override suspend fun all(): List<Customer> =
        repository.all()

    override suspend fun find(input: String, matcher: CustomerMatcher): Customer? =
        with(input.validateOrThrow(validator)) {
            when (matcher) {
                CustomerMatcher.Alias -> repository.findByAlias(alias = this)
                CustomerMatcher.Email -> repository.findByEmail(email = this)
                CustomerMatcher.Uuid -> repository.findByUuid(uuid = this)
            }
        }
}