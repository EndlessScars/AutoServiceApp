package com.android.autoservice.domain.model

data class Notification(
    val notificationId: Int,
    val name: String,
    val description: String,
    val date: String,
    val time: String
)