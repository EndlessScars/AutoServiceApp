package com.android.autoservice.domain.repository

import com.android.autoservice.domain.model.Notification

interface NotificationRepository {
    suspend fun getNotification(id: String): Notification
    suspend fun getNotificationList(): List<Notification>
    suspend fun createNotification(notification: Notification)
    suspend fun deleteNotification(id: String)
    suspend fun editNotification(id: String, notification: Notification)
}