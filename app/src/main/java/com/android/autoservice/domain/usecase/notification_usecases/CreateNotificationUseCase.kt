package com.android.autoservice.domain.usecase.notification_usecases

import com.android.autoservice.domain.model.Notification
import com.android.autoservice.domain.repository.NotificationRepository
import javax.inject.Inject

class CreateNotificationUseCase @Inject constructor(private val repository: NotificationRepository) {
    private suspend fun execute(notification: Notification){
        repository.createNotification(notification)
    }
}