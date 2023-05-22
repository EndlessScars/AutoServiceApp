package com.android.data.repository

import com.android.autoservice.data.mapper.Mapper
import com.android.autoservice.domain.model.Order
import com.android.autoservice.domain.model.Service
import com.android.domain.repository.OrderRepository
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class OrderRepositoryImpl @Inject constructor(private val firebaseDatabase: FirebaseDatabase) :
    OrderRepository {
    private val serviceKey = "Order"
    private val fireBaseRef = firebaseDatabase.getReference(serviceKey)
    private val mapper = Mapper()

    override suspend fun getOrderList(): List<Order> = suspendCoroutine { continuation ->
        fireBaseRef.get().addOnSuccessListener { snapshot ->
            val orderList = mutableListOf<Order>()
            for (orderDB in snapshot.children) {
                orderList.add(mapper.mapOrderDBToOrder(orderDB))
            }
            continuation.resume(orderList)
        }.addOnFailureListener {
            continuation.resumeWithException(it)
        }
    }

    override suspend fun getOrder(id: String): Order = suspendCoroutine { continuation ->
        fireBaseRef.child(id.toString()).get().addOnSuccessListener { snapshot ->
            val order = mapper.mapOrderDBToOrder(snapshot)
            continuation.resume(order)
        }.addOnFailureListener {
            continuation.resumeWithException(it)
        }
    }

    override suspend fun createOrder(order: Order) {
        fireBaseRef.push().setValue(order)
//        fireBaseRef.key?.let { fireBaseRef.child(it).setValue(order) }
    }

    override suspend fun editOrder(id: String, order: Order) {
        fireBaseRef.child(id).setValue(order)
    }
}