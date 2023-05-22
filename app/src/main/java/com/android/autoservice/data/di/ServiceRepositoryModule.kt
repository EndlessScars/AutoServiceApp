package com.android.autoservice.data.di

import com.android.autoservice.data.repository.ServiceRepositoryImpl
import com.android.autoservice.domain.repository.ServiceRepository
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ServiceRepositoryModule {

    @Provides
    fun provideServiceRepository(firebaseDatabase: FirebaseDatabase): ServiceRepository{
        return ServiceRepositoryImpl(firebaseDatabase)
    }
}