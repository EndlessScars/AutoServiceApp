package com.android.autoservice.data.repository

import com.android.autoservice.data.mapper.Mapper
import com.android.autoservice.domain.model.Notification
import com.android.autoservice.domain.repository.NotificationRepository
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class NotificationRepositoryImpl @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase
) : NotificationRepository {

    private val serviceKey = "Notification"
    private val fireBaseRef = firebaseDatabase.getReference(serviceKey)
    private val mapper = Mapper()

    override suspend fun getNotification(id: String): Notification =
        suspendCoroutine { continuation ->
            fireBaseRef.child(id.toString()).get().addOnSuccessListener { snapshot ->
                val notification = mapper.mapNotificationDBToNotification(snapshot)
                continuation.resume(notification)
            }.addOnFailureListener {
                continuation.resumeWithException(it)
            }
        }

    override suspend fun getNotificationList(): List<Notification> =
        suspendCoroutine { continuation ->
            fireBaseRef.get().addOnSuccessListener { snapshot ->
                val notificationList = mutableListOf<Notification>()
                for (notificationDB in snapshot.children) {
                    notificationList.add(mapper.mapNotificationDBToNotification(notificationDB))
                }
                continuation.resume(notificationList)
            }.addOnFailureListener {
                continuation.resumeWithException(it)
            }
        }


    override suspend fun createNotification(notification: Notification) {
        fireBaseRef.push().setValue(notification)
//        fireBaseRef.key?.let { fireBaseRef.child(it).setValue(notification) }
    }


    override suspend fun deleteNotification(id: String) {
        fireBaseRef.child(id).removeValue()
    }

    override suspend fun editNotification(id: String, notification: Notification) {
        fireBaseRef.child(id).setValue(notification)
    }
}