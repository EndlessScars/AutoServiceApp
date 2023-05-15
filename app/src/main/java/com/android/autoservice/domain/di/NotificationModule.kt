package com.android.autoservice.domain.di

import com.android.autoservice.domain.repository.NotificationRepository
import com.android.autoservice.domain.usecase.notification_usecases.CreateNotificationUseCase
import com.android.autoservice.domain.usecase.notification_usecases.DeleteNotificationUseCase
import com.android.autoservice.domain.usecase.notification_usecases.GetNotificationListUseCase
import com.android.autoservice.domain.usecase.notification_usecases.GetNotificationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class NotificationModule {

    @Provides
    fun provideCreateNotificationUseCase(repository: NotificationRepository):
            CreateNotificationUseCase =
        CreateNotificationUseCase(repository)

    @Provides
    fun provideDeleteNotificationUseCase(repository: NotificationRepository):
            DeleteNotificationUseCase =
        DeleteNotificationUseCase(repository)

    @Provides
    fun provideEditNotificationUseCase(repository: NotificationRepository):
            DeleteNotificationUseCase =
        DeleteNotificationUseCase(repository)

    @Provides
    fun provideGetNotificationListUseCase(repository: NotificationRepository):
            GetNotificationListUseCase =
        GetNotificationListUseCase(repository)

    @Provides
     fun provideGetNotificationUseCase(repository: NotificationRepository):
            GetNotificationUseCase =
         GetNotificationUseCase(repository)
}