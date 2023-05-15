package com.android.autoservice.presentation.view_models

import androidx.lifecycle.ViewModel
import com.android.autoservice.domain.model.User
import com.android.autoservice.domain.usecase.user_usecases.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
):ViewModel(){

    suspend fun getUser(login:String): User {
        return getUserUseCase.execute(login)
    }

    fun md5Transfer(password: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(password.toByteArray())).toString(16).padStart(32, '0')
    }
}