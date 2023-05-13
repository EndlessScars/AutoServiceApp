package com.android.autoservice.domain.repository

import com.android.autoservice.domain.model.Service

interface ServiceRepository {
    suspend fun getService(serviceId:Int): Service
    suspend fun getServiceList(): List<Service>
    suspend fun createService(service: Service)
    suspend fun editService(serviceId: Int)
    suspend fun deleteService(serviceId: Int)
}