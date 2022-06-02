package com.splanes.grocery.ms_customer.infraestructure.db.entity

import com.splanes.grocery.ms_customer.utils.primitive.orFalse
import org.springframework.data.annotation.Id


data class CustomerEntity(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Column(name = "id", nullable = false)
    var id: Long? = null,

    @field:GeneratedValue(generator = "uuid2")
    @field:GenericGenerator(name = "uuid2", strategy = "uuid2")
    @field:Column(name = "uuid", length = 36, unique = true, nullable = false)
    var uuid: String? = null,

    @field:Column(name = "email", nullable = false)
    var email: String? = null,

    @field:Column(name = "alias", nullable = false)
    var alias: String? = null,

    @field:Column(name = "pwd", nullable = false)
    var pwd: String? = null,

    @field:Column(name = "role", nullable = false)
    var role: String? = null,

    @field:Column(name = "date_millis_sign_up", nullable = false)
    var dateSignUp: Long? = null,

    @field:Column(name = "date_millis_last_sign_in", nullable = false)
    var dateLastSignIn: Long? = null,
) {
    override fun equals(other: Any?): Boolean =
        this === other || (other as? CustomerEntity)?.let { other.id == id || other.uuid == uuid }.orFalse()

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName +
                "(id = $id, " +
                "uuid = $uuid, " +
                "email = $email, " +
                "alias = $alias, " +
                "pwd = $pwd, " +
                "role = $role, " +
                "dateSignUp = $dateSignUp, " +
                "dateLastSignIn = $dateLastSignIn)"
    }
}
