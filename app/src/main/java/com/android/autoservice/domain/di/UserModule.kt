package com.android.autoservice.domain.di

import com.android.autoservice.domain.repository.UserRepository
import com.android.autoservice.domain.usecase.user_usecases.CreateUserUseCase
import com.android.autoservice.domain.usecase.user_usecases.GetUserListUseCase
import com.android.autoservice.domain.usecase.user_usecases.GetUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UserModule {

    @Provides
    fun provideCreateUserUseCase(repository: UserRepository): CreateUserUseCase {
        return CreateUserUseCase(repository)
    }

    @Provides
    fun provideGetUserListUseCase(repository: UserRepository): GetUserListUseCase {
        return GetUserListUseCase(repository)
    }

    @Provides
    fun provideGetUserUseCase(repository: UserRepository): GetUserUseCase {
        return GetUserUseCase(repository)
    }
}