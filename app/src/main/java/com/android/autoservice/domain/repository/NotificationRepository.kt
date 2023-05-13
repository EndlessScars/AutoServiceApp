package com.android.autoservice.domain.repository

import com.android.autoservice.domain.model.Notification

interface NotificationRepository {
    suspend fun getNotification(notificationId: Int): Notification
    suspend fun getNotificationList(): List<Notification>
    suspend fun createNotification(notification: Notification)
    suspend fun deleteNotification(notificationId: Int)
    suspend fun editNotification(notificationId: Int)
}