package com.android.data.di

import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {
    @Provides @Singleton
     fun provideFirebaseInstance(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }
}