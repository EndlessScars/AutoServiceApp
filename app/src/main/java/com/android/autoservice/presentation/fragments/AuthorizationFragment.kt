package com.android.autoservice.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.android.autoservice.R
import com.android.autoservice.databinding.FragmentAuthorizationBinding
import com.android.autoservice.presentation.view_models.AuthorizationViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.math.BigInteger
import java.security.MessageDigest

@AndroidEntryPoint
class AuthorizationFragment : Fragment() {

    lateinit var viewBinding: FragmentAuthorizationBinding
    private val USER_KEY = "User"
    private val dataBase = Firebase.database
    private val fireBaseRef = dataBase.getReference(USER_KEY)
    private val viewModel: AuthorizationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentAuthorizationBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.btnLogin.setOnClickListener() {
            val login = viewBinding.etLogin.text.toString()
            val password = viewBinding.etPassword.text.toString()

            lifecycleScope.launch {
                val user = viewModel.getUser(login)
                if (user.password == viewModel.md5Transfer(password)) {
                        view.findNavController()
                            .navigate(R.id.action_authorizationFragment_to_clientFragment)
                    }
            }
        }

        viewBinding.btnRegistration.setOnClickListener() {
            view.findNavController()
                .navigate(R.id.action_authorizationFragment_to_registrationFragment)
        }
    }

    fun md5Transfer(password: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(password.toByteArray())).toString(16).padStart(32, '0')
    }
}