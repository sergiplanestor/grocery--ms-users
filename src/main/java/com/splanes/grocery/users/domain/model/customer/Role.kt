package com.splanes.grocery.users.domain.model.user

import com.splanes.grocery.users.utils.primitive.capitalize
import com.splanes.grocery.users.utils.primitive.mapIf
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

enum class Role(private val value: String, private val description: String) {
    Sudo(value = "sudo", description = "System administrator role who has granted all existing privileges."),
    System(value = "system", description = "System internal role. Only can access to internal server resources such as Swagger."),
    Client(value = "client", description = "Customer role."),
    Anonymous(value = "anonymous", description = "");

    companion object;

    fun value() =
        this.value

    fun toString(detailed: Boolean) =
        toString().mapIf(detailed) {
            buildString {
                append("[")
                append(this@Role.toString())
                append(": ")
                append(description)
                append("]")
            }
        }

    override fun toString(): String {
        return value().capitalize()
    }
}

inline val Role.authority: GrantedAuthority get() =
    SimpleGrantedAuthority(toString())

fun Role.authorities(): List<GrantedAuthority> =
    listOf(authority)

fun Role.Companion.grantedAuthorityOf(name: String?): GrantedAuthority =
    fromNameOrElse(name).authority

fun Role.Companion.fromNameOrElse(name: String?, default: Role = Role.Client): Role =
    Role.values().associateBy { it.value() }.getOrDefault(name?.lowercase(), default)