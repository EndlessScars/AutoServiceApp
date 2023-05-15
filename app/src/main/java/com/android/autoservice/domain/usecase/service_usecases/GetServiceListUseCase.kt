package com.android.autoservice.domain.usecase.service_usecases

import com.android.autoservice.domain.model.Service
import com.android.autoservice.domain.repository.ServiceRepository
import javax.inject.Inject

class GetServiceListUseCase @Inject constructor(private val repository: ServiceRepository) {
    suspend fun execute(): List<Service>{
       return repository.getServiceList()
    }
}