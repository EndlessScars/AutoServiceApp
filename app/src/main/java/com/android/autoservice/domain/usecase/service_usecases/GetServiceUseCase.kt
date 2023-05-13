package com.android.autoservice.domain.usecase.service_usecases

import com.android.autoservice.domain.model.Service
import com.android.autoservice.domain.repository.ServiceRepository
import javax.inject.Inject

class GetServiceUseCase @Inject constructor(private val repository: ServiceRepository) {
    private suspend fun execute(serviceId: Int):Service{
        return repository.getService(serviceId)
    }
}