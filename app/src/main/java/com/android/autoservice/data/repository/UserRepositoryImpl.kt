package com.android.autoservice.data.repository

import com.android.autoservice.data.mapper.Mapper
import com.android.autoservice.domain.model.User
import com.android.autoservice.domain.repository.UserRepository
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class UserRepositoryImpl @Inject constructor(private val firebaseDatabase: FirebaseDatabase) :
    UserRepository {

    private val userKey = "User"
    private val fireBaseRef = firebaseDatabase.getReference(userKey)
    private val mapper = Mapper()

    override suspend fun createUser(login: String, user: User) {
        fireBaseRef.child(login).setValue(user)
    }

    override suspend fun getUser(login: String): User = suspendCoroutine { continuation ->
        fireBaseRef.child(login).get().addOnSuccessListener { snapshot ->
            val user = mapper.mapUserDBToUser(snapshot)
            continuation.resume(user)
        }.addOnFailureListener{
            continuation.resumeWithException(it)
        }
    }

    override suspend fun getUserList(): List<User> = suspendCoroutine { continuation ->
            fireBaseRef.get().addOnSuccessListener { snapshot ->
                val userList = mutableListOf<User>()
                for(userDB in snapshot.children) {
                    userList.add(mapper.mapUserDBToUser(userDB))
                }
                continuation.resume(userList.toList())
            }.addOnFailureListener{
                continuation.resumeWithException(it)
            }
        }
    }