package com.splanes.grocery.ms_customer.domain.model.customer

import kotlin.reflect.KParameter
import kotlin.reflect.KProperty

interface FindBy<T, P> {
    fun T.propertyMatcher(): KProperty<P>
    infix fun KProperty<P>.matches(other: KProperty<P>): Boolean = this.value() == other.value()
}

fun <T> KProperty<T>.value(vararg params: KParameter): T =
    with(getter) {
        if (parameters.isNotEmpty() && parameters.count() > params.count()) {
            throw RuntimeException("Getter params not provided...")
        } else {
            call()
        }
    }

fun <P, T : FindBy<T, P>> T.matches(other: T): Boolean =
    propertyMatcher() matches other.propertyMatcher()

sealed class CustomerMatcher {

    fun findBy(): FindBy<Customer, String> = when(this) {
        Uuid -> Uuid
        Alias -> Alias
        Email -> Email
    }

    object Uuid : CustomerMatcher(), FindBy<Customer, String> {
        override fun Customer.propertyMatcher(): KProperty<String> = ::uuid
    }
    object Alias : CustomerMatcher(), FindBy<Customer, String> {
        override fun Customer.propertyMatcher(): KProperty<String> = ::alias
    }
    object Email : CustomerMatcher(), FindBy<Customer, String> {
        override fun Customer.propertyMatcher(): KProperty<String> = ::email
    }
}
