package com.android.autoservice.domain.model

import com.android.autoservice.utils.OrderStatus

data class Order(
    val id: Int,
    val description: String,
    val creationDate: String,
    val closingDate: String,
    val materialCost: Float,
    val materialList: String,
    val status: OrderStatus
)