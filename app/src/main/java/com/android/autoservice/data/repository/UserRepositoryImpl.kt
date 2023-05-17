package com.android.autoservice.data.repository

import com.android.autoservice.data.mapper.UserMapper
import com.android.autoservice.domain.model.User
import com.android.autoservice.domain.repository.UserRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resumeWithException

class UserRepositoryImpl @Inject constructor(private val firebaseDatabase: FirebaseDatabase) :
    UserRepository {

    private val userKey = "User"
    private val fireBaseRef = firebaseDatabase.getReference(userKey)
    private val mapper = UserMapper()

    override suspend fun createUser(login: String, user: User) {
        fireBaseRef.child(login).setValue(user)
    }

    override suspend fun getUser(login: String): User{
        var user: User = User(" ","", "")

        fireBaseRef.child(login).get().addOnSuccessListener {
            user = mapper.mapUserDBToUser(it)
        }.addOnFailureListener{
            throw it
        }
        return user
    }

//    @OptIn(ExperimentalCoroutinesApi::class)
//    override suspend fun getUser(login: String): User {
//        return suspendCancellableCoroutine { continuation ->
//            val userRef = fireBaseRef.child(login)
//
//            val valueEventListener = object : ValueEventListener {
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    val user = mapper.mapUserDBToUser(snapshot)
//                    continuation.resume(user, null)
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                    continuation.resumeWithException(error.toException())
//                }
//            }
//
//            userRef.addListenerForSingleValueEvent(valueEventListener)
//
//            continuation.invokeOnCancellation {
//                userRef.removeEventListener(valueEventListener)
//            }
//        }
//    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getUserList(): List<User> {
        return suspendCancellableCoroutine { continuation ->

            val valueEventListener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val userList = mutableListOf<User>()
                    for (childSnapshot in snapshot.children) {
                        val user = mapper.mapUserDBToUser(snapshot)
                        userList.add(user)
                    }
                    continuation.resume(userList, null)
                }

                override fun onCancelled(error: DatabaseError) {
                    continuation.resumeWithException(error.toException())
                }
            }

            fireBaseRef.addListenerForSingleValueEvent(valueEventListener)

            continuation.invokeOnCancellation {
                fireBaseRef.removeEventListener(valueEventListener)
            }
        }
    }
}