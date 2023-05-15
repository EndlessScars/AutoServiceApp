package com.android.autoservice.data.repository

import com.android.autoservice.data.mapper.UserMapper
import com.android.autoservice.domain.model.User
import com.android.autoservice.domain.repository.UserRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.*
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

    override suspend fun getUser(login: String): User {
        return suspendCancellableCoroutine { continuation ->
            val userRef = fireBaseRef.child(login)
            userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        val user = dataSnapshot.getValue(User::class.java)
                        if (user != null) {
                            continuation.resume(user, null)
                        } else {
                            continuation.resumeWithException(Exception("Failed to parse user data"))
                        }
                    } else {
                        continuation.resumeWithException(Exception("User not found"))
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    continuation.resumeWithException(databaseError.toException())
                }
            })
        }
    }



    override suspend fun getUserList(): List<User> {
        TODO("Not yet implemented")
    }
}

