package com.android.autoservice.data.mapper

import com.android.autoservice.domain.model.User
import com.google.firebase.database.DataSnapshot

class UserMapper {


    fun mapUserDBToUser(dataSnapshot: DataSnapshot): User {
        return User(
            name = dataSnapshot.child("name").value.toString(),
            password = dataSnapshot.child("password").value.toString(),
            userType = dataSnapshot.child("userType").value.toString()
        )
    }
}