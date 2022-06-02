package com.splanes.grocery.ms_customer.infraestructure.mapper

import com.splanes.grocery.ms_customer.domain.model.customer.Customer
import com.splanes.grocery.ms_customer.domain.model.customer.CustomerSimple
import com.splanes.grocery.ms_customer.domain.model.customer.Role
import com.splanes.grocery.ms_customer.infraestructure.db.entity.CustomerEntity
import com.splanes.grocery.ms_customer.utils.date.dateOf

class CustomerMapper {

    fun toEntity(customer: Customer): CustomerEntity =
        CustomerEntity(
            uuid = customer.uuid,
            email = customer.email,
            alias = customer.alias,
            pwd = customer.password,
            role = customer.role.value(),
            dateSignUp = customer.dateSignUp.time,
            dateLastSignIn = customer.dateLastSignIn.time,
        )

    fun toCustomer(entity: CustomerEntity): Customer =
        Customer(
            uuid = entity.uuid.orEmpty(),
            email = entity.email.orEmpty(),
            alias = entity.alias.orEmpty(),
            password = entity.pwd.orEmpty(),
            role = Role.parseOrDefault(entity.role),
            dateSignUp = dateOf(entity.dateSignUp),
            dateLastSignIn = dateOf(entity.dateLastSignIn)
        )

    fun toCustomerSimple(entity: CustomerEntity): CustomerSimple =
        CustomerSimple(
            uuid = entity.uuid.orEmpty(),
            email = entity.email.orEmpty(),
            alias = entity.alias.orEmpty(),
            dateSignUp = dateOf(entity.dateSignUp),
            dateLastSignIn = dateOf(entity.dateLastSignIn)
        )
}