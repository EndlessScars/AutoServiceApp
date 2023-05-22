package com.android.autoservice.domain.model

import com.android.autoservice.utils.OrderStatus

data class Order(
    val orderUser: String,
    val description: String,
    val creationDate: String,
    val closingDate: String,
    val materialCost: Float,
    val materialList: String,
    val status: String,
    val masterLogin: String,
    val clientLogin: String
)