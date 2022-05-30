package com.splanes.grocery.users.domain.repository

import com.splanes.grocery.users.domain.model.user.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun save(user: User): User
    fun all(): Flow<User>
    suspend fun findByEmail(email: String): User?
}