package com.android.autoservice.domain.di

import com.android.autoservice.domain.repository.ServiceRepository
import com.android.autoservice.domain.usecase.service_usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ServiceModule {

    @Provides
    fun provideCreateServiceUseCase(repository: ServiceRepository): CreateServiceUseCase {
        return CreateServiceUseCase(repository)
    }

    @Provides
    fun provideDeleteServiceUseCase(repository: ServiceRepository): DeleteServiceUseCase {
        return DeleteServiceUseCase(repository)
    }

    @Provides
    fun provideEditServiceUseCase(repository: ServiceRepository): EditServiceUseCase {
        return EditServiceUseCase(repository)
    }

    @Provides
    fun provideGetServiceUseCase(repository: ServiceRepository): GetServiceUseCase {
        return GetServiceUseCase(repository)
    }

    @Provides
    fun provideGetServiceListUseCase(repository: ServiceRepository):
            GetServiceListUseCase {
        return GetServiceListUseCase(repository)
    }
}