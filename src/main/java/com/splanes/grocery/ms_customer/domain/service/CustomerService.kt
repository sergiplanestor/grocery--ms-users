package com.splanes.grocery.ms_customer.domain.service

import com.splanes.grocery.ms_customer.domain.model.customer.Customer
import com.splanes.grocery.ms_customer.domain.model.customer.CustomerMatcher
import javax.validation.constraints.NotBlank


interface CustomerService {
    suspend fun persist(customer: Customer): Customer
    suspend fun all(): List<Customer>
    suspend fun find(@NotBlank input: String, matcher: CustomerMatcher): Customer?
}