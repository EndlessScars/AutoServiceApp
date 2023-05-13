package com.android.autoservice.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.android.autoservice.R
import com.android.autoservice.databinding.FragmentAuthorizationBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.math.BigInteger
import java.security.MessageDigest


class AuthorizationFragment : Fragment() {

    lateinit var viewBinding: FragmentAuthorizationBinding
    private val USER_KEY = "User"
    private val dataBase = Firebase.database
    private val fireBaseRef = dataBase.getReference(USER_KEY)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentAuthorizationBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.btnLogin.setOnClickListener(){
            val login = viewBinding.etLogin.text.toString()
            val password = viewBinding.etPassword.text.toString()

            fireBaseRef.child(login).get().addOnSuccessListener {
                if (it.child("password").value.toString() == md5Transfer(password)){
                    view.findNavController()
                        .navigate(R.id.action_authorizationFragment_to_clientFragment)
                }
            }.addOnFailureListener{
                Log.e("firebase", "Error getting data", it)
            }

        }

        viewBinding.btnRegistration.setOnClickListener() {
            view.findNavController()
                .navigate(R.id.action_authorizationFragment_to_registrationFragment)
        }
    }
    fun md5Transfer(password:String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(password.toByteArray())).toString(16).padStart(32, '0')
    }
}