package com.android.autoservice.domain.usecase.notification_usecases

import com.android.autoservice.domain.model.Notification
import com.android.autoservice.domain.repository.NotificationRepository
import javax.inject.Inject

class GetNotificationListUseCase @Inject constructor(private val repository: NotificationRepository) {
    private suspend fun execute(): List<Notification>{
        return repository.getNotificationList()
    }
}