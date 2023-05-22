package com.android.autoservice.domain.usecase.service_usecases

import com.android.autoservice.domain.model.Service
import com.android.autoservice.domain.repository.ServiceRepository
import javax.inject.Inject

class EditServiceUseCase @Inject constructor(
    private val repository: ServiceRepository,
) {
    suspend fun execute(id: String, service: Service) {
        repository.editService(id, service)
    }
}