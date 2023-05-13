package com.android.presentation

import androidx.lifecycle.ViewModel
import com.android.autoservice.domain.usecase.order_usecases.GetOrderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(private val getOrderUseCase: GetOrderUseCase) :
    ViewModel() {
}