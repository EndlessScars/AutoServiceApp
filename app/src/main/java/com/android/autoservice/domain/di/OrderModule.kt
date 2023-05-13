//package com.android.domain.di
//
//import com.android.domain.repository.OrderRepository
//import com.android.domain.usecase.GetOrderUseCase
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//
//@Module
//@InstallIn(SingletonComponent::class)
//class OrderModule {
//    @Provides
//    private fun provideGetOrderUseCase(repository: OrderRepository): GetOrderUseCase {
//        return GetOrderUseCase(repository)
//    }
//}