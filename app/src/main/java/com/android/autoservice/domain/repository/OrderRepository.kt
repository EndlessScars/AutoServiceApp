package com.android.domain.repository

import com.android.autoservice.domain.model.Order

interface OrderRepository {
    suspend fun getOrderList():List<Order>
    suspend fun getOrder(id: String):Order
    suspend fun createOrder(order: Order)
    suspend fun editOrder(id: String, order: Order)
}