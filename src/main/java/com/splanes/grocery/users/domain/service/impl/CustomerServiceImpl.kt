package com.splanes.grocery.users.domain.service.impl

import com.splanes.grocery.users.domain.model.customer.Customer
import com.splanes.grocery.users.domain.model.customer.CustomerMatcher
import com.splanes.grocery.users.domain.repository.CustomerRepository
import com.splanes.grocery.users.domain.service.CustomerService
import org.springframework.stereotype.Service

@Service
class CustomerServiceImpl(private val repository: CustomerRepository) : CustomerService {

    override suspend fun persist(customer: Customer): Customer {
        TODO("Not yet implemented")
    }

    override suspend fun all(): List<Customer> {
        TODO("Not yet implemented")
    }

    override suspend fun find(value: String, matcher: CustomerMatcher): Customer? {
        TODO("Not yet implemented")
    }
}