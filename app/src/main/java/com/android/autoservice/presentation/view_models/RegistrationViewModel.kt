package com.android.autoservice.presentation.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.autoservice.domain.model.User
import com.android.autoservice.domain.usecase.user_usecases.CreateUserUseCase
import com.android.autoservice.domain.usecase.user_usecases.GetUserListUseCase
import com.android.autoservice.domain.usecase.user_usecases.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val createUserUseCase: CreateUserUseCase,
) :
    ViewModel() {


    suspend fun createUser(login: String, user: User) = coroutineScope {
        createUserUseCase.execute(login, user)
    }

    suspend fun getUser(login:String): User{
        return getUserUseCase.execute(login)
    }

    fun md5Transfer(password: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(password.toByteArray())).toString(16).padStart(32, '0')
    }
}