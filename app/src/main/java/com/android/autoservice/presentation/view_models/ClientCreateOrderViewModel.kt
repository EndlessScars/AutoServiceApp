package com.android.autoservice.presentation.view_models

import androidx.lifecycle.ViewModel
import com.android.autoservice.domain.model.Order
import com.android.autoservice.domain.usecase.order_usecases.CreateOrderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ClientCreateOrderViewModel @Inject constructor(
    private val createOrderUseCase: CreateOrderUseCase
): ViewModel() {

    suspend fun createOrder(order: Order){
        createOrderUseCase.execute(order)
    }
}