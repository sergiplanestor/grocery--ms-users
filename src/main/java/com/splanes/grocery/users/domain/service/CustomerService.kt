package com.splanes.grocery.users.domain.service

import com.splanes.grocery.users.domain.model.customer.Customer
import com.splanes.grocery.users.domain.model.customer.CustomerMatcher


interface CustomerService {
    suspend fun persist(customer: Customer): Customer
    suspend fun all(): List<Customer>
    suspend fun find(value: String, matcher: CustomerMatcher): Customer?
}