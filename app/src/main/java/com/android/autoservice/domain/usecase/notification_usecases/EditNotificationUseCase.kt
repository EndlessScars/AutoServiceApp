package com.android.autoservice.domain.usecase.notification_usecases

import com.android.autoservice.domain.repository.NotificationRepository
import javax.inject.Inject

class EditNotificationUseCase @Inject constructor(private val repository: NotificationRepository) {
    private suspend fun execute(notificationId: Int){
        repository.editNotification(notificationId)
    }
}