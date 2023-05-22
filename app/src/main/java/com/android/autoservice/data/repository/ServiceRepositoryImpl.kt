package com.android.autoservice.data.repository

import com.android.autoservice.data.mapper.Mapper
import com.android.autoservice.domain.model.Service
import com.android.autoservice.domain.repository.ServiceRepository
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class ServiceRepositoryImpl @Inject constructor(private val firebaseDatabase: FirebaseDatabase) :
    ServiceRepository {
    private val serviceKey = "Service"
    private val fireBaseRef = firebaseDatabase.getReference(serviceKey)
    private val mapper = Mapper()

    override suspend fun getService(serviceId: String): Service = suspendCoroutine { continuation ->
        fireBaseRef.child(serviceId.toString()).get().addOnSuccessListener { snapshot ->
            val service = mapper.mapServiceDBToService(snapshot)
            continuation.resume(service)
        }.addOnFailureListener {
            continuation.resumeWithException(it)
        }
    }

    override suspend fun getServiceList(): List<Service> = suspendCoroutine { continuation ->
        fireBaseRef.get().addOnSuccessListener { snapshot ->
            val serviceList = mutableListOf<Service>()
            for (serviceDB in snapshot.children) {
                serviceList.add(mapper.mapServiceDBToService(serviceDB))
            }
            continuation.resume(serviceList)
        }.addOnFailureListener {
            continuation.resumeWithException(it)
        }
    }

    override suspend fun createService(service: Service) {
        fireBaseRef.key?.let { fireBaseRef.child(it).setValue(service) }
    }

    override suspend fun editService(serviceId: String, service: Service) {
        fireBaseRef.child(serviceId).setValue(service)
    }

    override suspend fun deleteService(serviceId: String) {
        fireBaseRef.child(serviceId).removeValue()
    }
}