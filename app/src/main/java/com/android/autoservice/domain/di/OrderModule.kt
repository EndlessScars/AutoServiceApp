package com.android.domain.di

import com.android.autoservice.domain.usecase.order_usecases.CreateOrderUseCase
import com.android.autoservice.domain.usecase.order_usecases.EditOrderUseCase
import com.android.autoservice.domain.usecase.order_usecases.GetOrderListUseCase
import com.android.autoservice.domain.usecase.order_usecases.GetOrderUseCase
import com.android.domain.repository.OrderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class OrderModule {

    @Provides
    fun provideGetOrderUseCase(repository: OrderRepository): GetOrderUseCase{
        return GetOrderUseCase(repository)
    }


    @Provides
    fun provideGetOderListUseCase(repository: OrderRepository): GetOrderListUseCase {
        return GetOrderListUseCase(repository)
    }

    @Provides
    fun provideCreateOrderUserCase(repository: OrderRepository): CreateOrderUseCase {
        return CreateOrderUseCase(repository)
    }


    @Provides
    fun provideEditOrderUseCase(repository: OrderRepository): EditOrderUseCase {
        return EditOrderUseCase(repository)
    }
}