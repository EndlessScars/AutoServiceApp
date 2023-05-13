package com.android.autoservice.domain.usecase.order_usecases
import com.android.autoservice.domain.model.Order
import com.android.domain.repository.OrderRepository
import javax.inject.Inject


class GetOrderUseCase @Inject constructor(private val repository: OrderRepository) {
    private suspend fun execute(orderId:Int): Order {
        return repository.getOrder(orderId)
    }
}