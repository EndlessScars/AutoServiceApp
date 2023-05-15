package com.android.data.repository

import com.android.autoservice.domain.model.Order
import com.android.domain.repository.OrderRepository
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(private val firebaseDatabase: FirebaseDatabase) :
    OrderRepository {
    override suspend fun getOrderList(): List<Order> {
        TODO("Not yet implemented")
    }

    override suspend fun getOrder(orderId: Int): Order {
        TODO("Not yet implemented")
    }

    override suspend fun createOrder(order: Order) {
        TODO("Not yet implemented")
    }

    override suspend fun editOrder(orderId: Int) {
        TODO("Not yet implemented")
    }
}