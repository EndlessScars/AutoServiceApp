package com.android.autoservice.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.autoservice.domain.model.Service
import com.android.autoservice.domain.usecase.service_usecases.GetServiceListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ServicesListViewModel @Inject constructor(
    private val getServiceListUseCase: GetServiceListUseCase
) :
    ViewModel() {

    private val serviceListMutableLiveData = MutableLiveData<List<Service>>()
    val serviceListLiveData: LiveData<List<Service>> = serviceListMutableLiveData

    suspend fun getServiceList() {
        serviceListMutableLiveData.value = getServiceListUseCase.execute().toList()
    }
}