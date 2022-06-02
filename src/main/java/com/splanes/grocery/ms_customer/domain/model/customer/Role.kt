package com.splanes.grocery.ms_customer.domain.model.customer

import com.splanes.grocery.ms_customer.utils.primitive.capitalize
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

enum class Role(private val value: String) {
    Admin(value = "sudo"),
    Customer(value = "customer");
    companion object {
        fun parseOrDefault(name: String?, default: Role = Customer): Role =
            values().associateBy { e -> e.value() }.getOrDefault(name?.lowercase(), default)
    }

    fun value() = this.value

    override fun toString(): String {
        return value().capitalize()
    }
}

inline val Role.authority: GrantedAuthority get() =
    SimpleGrantedAuthority(toString())

fun Role.Companion.grantedAuthorityOf(name: String?): GrantedAuthority =
    parseOrDefault(name).authority