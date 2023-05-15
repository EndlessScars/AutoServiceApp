package com.android.autoservice.data.repository

import com.android.autoservice.data.mapper.UserMapper
import com.android.autoservice.domain.model.User
import com.android.autoservice.domain.repository.UserRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resumeWithException

class UserRepositoryImpl @Inject constructor(private val firebaseDatabase: FirebaseDatabase) :
    UserRepository {

    private val userKey = "User"
    private val fireBaseRef = firebaseDatabase.getReference(userKey)
    private val mapper = UserMapper()

    override suspend fun createUser(login: String, user: User,) {
        fireBaseRef.child(login).setValue(user)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getUser(login: String): User = suspendCancellableCoroutine { continuation ->
        fireBaseRef.child("User").child(login).get().addOnSuccessListener { dataSnapshot ->
            val user: User = mapper.mapUserDBToUser(dataSnapshot)
            continuation.resume(user, null)
        }.addOnFailureListener { exception ->
            continuation.resumeWithException(exception)
        }
    }

    override suspend fun getUserList(): List<User> {
        TODO("Not yet implemented")
    }
}

