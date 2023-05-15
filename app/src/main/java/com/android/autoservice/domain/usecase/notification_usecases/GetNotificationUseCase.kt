package com.android.autoservice.domain.usecase.notification_usecases

import com.android.autoservice.domain.model.Notification
import com.android.autoservice.domain.repository.NotificationRepository
import javax.inject.Inject

class GetNotificationUseCase @Inject constructor(private val repository: NotificationRepository) {
    suspend fun execute(notificationId:Int):Notification{
        return repository.getNotification(notificationId)
    }
}