package com.android.autoservice.domain.usecase.user_usecases

import com.android.autoservice.domain.model.User
import com.android.autoservice.domain.repository.UserRepository
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(private val repository: UserRepository) {
    suspend fun execute() : List<User>{
        return repository.getUserList()
    }
}