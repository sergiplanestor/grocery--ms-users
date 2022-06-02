package com.splanes.grocery.ms_customer.domain.repository

import com.splanes.grocery.ms_customer.domain.model.customer.Customer

interface CustomerRepository {
    suspend fun persist(customer: Customer): Customer
    suspend fun all(): List<Customer>
    suspend fun findByUuid(uuid: String): Customer?
    suspend fun findByAlias(alias: String): Customer?
    suspend fun findByEmail(email: String): Customer?
}