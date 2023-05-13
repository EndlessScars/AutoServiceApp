package com.android.autoservice.domain.model

import com.android.autoservice.utils.UserType

data class User(
    val name: String,
    val password: String,
    val userType: UserType
)
