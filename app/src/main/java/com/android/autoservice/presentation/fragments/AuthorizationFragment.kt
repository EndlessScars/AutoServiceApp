package com.android.autoservice.presentation.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.android.autoservice.R
import com.android.autoservice.databinding.FragmentAuthorizationBinding
import com.android.autoservice.presentation.view_models.AuthorizationViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.math.BigInteger
import java.security.MessageDigest

@AndroidEntryPoint
class AuthorizationFragment : Fragment() {

    lateinit var viewBinding: FragmentAuthorizationBinding
    private val viewModel: AuthorizationViewModel by viewModels()
    val savedLogin: SharedPreferences? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentAuthorizationBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressBar: ProgressBar =
            requireActivity().findViewById<ProgressBar>(R.id.progressBar)

        viewBinding.btnLogin.setOnClickListener() {
            val login = viewBinding.etLogin.text.toString()
            val password = viewBinding.etPassword.text.toString()

            lifecycleScope.launch {
                progressBar.visibility = View.VISIBLE
                val user = viewModel.getUser(login)
                if (user.password == viewModel.md5Transfer(password)) {

                    when (user.userType) {
                        "CLIENT" ->
                            view.findNavController()
                                .navigate(R.id.action_authorizationFragment_to_clientFragment)

                        "MASTER" ->
                            view.findNavController()
                                .navigate(R.id.action_authorizationFragment_to_masterFragment)

                        "ADMINISTRATOR" ->
                            view.findNavController()
                                .navigate(R.id.action_authorizationFragment_to_adminFragment)

                    }
                }
                progressBar.visibility = View.INVISIBLE
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