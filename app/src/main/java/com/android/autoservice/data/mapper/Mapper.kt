package com.android.autoservice.data.mapper

import com.android.autoservice.domain.model.Notification
import com.android.autoservice.domain.model.Order
import com.android.autoservice.domain.model.Service
import com.android.autoservice.domain.model.User
import com.google.firebase.database.DataSnapshot

class Mapper {

    fun mapUserDBToUser(dataSnapshot: DataSnapshot): User {
        return User(
            name = dataSnapshot.child("name").value.toString(),
            password = dataSnapshot.child("password").value.toString(),
            userType = dataSnapshot.child("userType").value.toString()
        )
    }

    fun mapServiceDBToService(dataSnapshot: DataSnapshot): Service {
        return Service(
            id = dataSnapshot.child("id").value.toString(),
            name = dataSnapshot.child("name").value.toString(),
            price= dataSnapshot.child("price").value.toString().toInt()
        )
    }

    fun mapOrderDBToOrder(dataSnapshot: DataSnapshot): Order{
        return Order(
            orderUser = dataSnapshot.child("orderUser").value.toString(),
            description = dataSnapshot.child("description").value.toString(),
            creationDate = dataSnapshot.child("creationDate").value.toString(),
            closingDate = dataSnapshot.child("closingDate").value.toString(),
            materialCost = dataSnapshot.child("materialCost").value.toString().toFloat(),
            materialList = dataSnapshot.child("materialList").value.toString(),
            status = dataSnapshot.child("status").value.toString(),
            masterLogin = dataSnapshot.child("masterLogin").value.toString(),
            clientLogin = dataSnapshot.child("clientLogin").value.toString()
        )
    }

    fun mapNotificationDBToNotification(dataSnapshot: DataSnapshot): Notification{
        return Notification(
            name = dataSnapshot.child("name").value.toString(),
            description = dataSnapshot.child("description").value.toString(),
            date = dataSnapshot.child("date").value.toString(),
            time = dataSnapshot.child("time").value.toString(),
        )
    }
}