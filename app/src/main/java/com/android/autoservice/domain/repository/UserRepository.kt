package com.android.autoservice.domain.repository

import com.android.autoservice.domain.model.User

interface UserRepository {
    suspend fun createUser(login:String, user: User)
    suspend fun getUser(login:String): User
    suspend fun getUserList():List<User>
}