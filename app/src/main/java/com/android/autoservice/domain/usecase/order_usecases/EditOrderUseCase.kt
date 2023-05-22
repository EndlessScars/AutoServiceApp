package com.android.autoservice.domain.usecase.order_usecases

import com.android.autoservice.domain.model.Order
import com.android.domain.repository.OrderRepository
import javax.inject.Inject

class EditOrderUseCase @Inject constructor(
    private val repository: OrderRepository
) {
    suspend fun execute(id: String, order: Order) {
        repository.editOrder(id, order)
    }
}