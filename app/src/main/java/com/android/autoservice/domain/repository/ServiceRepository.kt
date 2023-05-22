package com.android.autoservice.domain.repository

import com.android.autoservice.domain.model.Service

interface ServiceRepository {
    suspend fun getService(id:String): Service
    suspend fun getServiceList(): List<Service>
    suspend fun createService(service: Service)
    suspend fun editService(id: String, service: Service)
    suspend fun deleteService(id: String)
}