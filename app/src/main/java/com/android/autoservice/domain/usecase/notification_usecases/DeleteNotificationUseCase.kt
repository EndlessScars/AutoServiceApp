package com.android.autoservice.domain.usecase.notification_usecases

import com.android.autoservice.domain.repository.NotificationRepository
import javax.inject.Inject

class DeleteNotificationUseCase @Inject constructor(private val repository: NotificationRepository) {
    suspend fun execute(id: String){
        repository.deleteNotification(id)
    }
}