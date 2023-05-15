package com.android.autoservice.data.di

import com.android.autoservice.data.repository.UserRepositoryImpl
import com.android.autoservice.domain.repository.UserRepository
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UserRepositoryModule {

    @Provides
    fun provideUserRepository(firebaseDatabase: FirebaseDatabase): UserRepository {
        return UserRepositoryImpl(firebaseDatabase)
    }
}
