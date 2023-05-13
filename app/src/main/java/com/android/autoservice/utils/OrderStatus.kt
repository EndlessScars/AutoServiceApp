package com.android.autoservice.utils

enum class OrderStatus(val orderStatus: String) {
    WAITING_ACCEPTANCE("Ожидает принятие"),
    ORDER_CANCELED("Заказ отменен"),
    WAITING_CUSTOMER("Ожидание клиента"),
    WAITING_MATERIALS("Ожидание запчастей"),
    IN_WORK("В работе"),
    DONE("Выполнен")
}