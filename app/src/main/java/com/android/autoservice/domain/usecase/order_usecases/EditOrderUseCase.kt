package com.android.autoservice.domain.usecase.order_usecases

import com.android.domain.repository.OrderRepository
import javax.inject.Inject

class EditOrderUseCase @Inject constructor(
    private val repository: OrderRepository
) {
    private suspend fun execute(orderId: Int) {
        repository.editOrder(orderId)
    }
}