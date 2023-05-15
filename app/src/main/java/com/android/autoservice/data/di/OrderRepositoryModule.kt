package com.android.data.di

import com.android.data.repository.OrderRepositoryImpl
import com.android.domain.repository.OrderRepository
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class OrderRepositoryModule {

    @Provides
    fun provideOrderRepository(firebaseDatabase: FirebaseDatabase): OrderRepository {
        return OrderRepositoryImpl(firebaseDatabase)
    }
}