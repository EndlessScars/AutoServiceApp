package com.android.autoservice.domain.usecase.notification_usecases

import com.android.autoservice.domain.model.Notification
import com.android.autoservice.domain.repository.NotificationRepository
import javax.inject.Inject

class EditNotificationUseCase @Inject constructor(private val repository: NotificationRepository) {
    suspend fun execute(id: String, notification: Notification){
        repository.editNotification(id, notification)
    }
}