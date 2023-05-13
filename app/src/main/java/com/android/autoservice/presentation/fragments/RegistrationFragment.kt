package com.android.autoservice.presentation.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.android.autoservice.R
import com.android.autoservice.databinding.FragmentRegistrationBinding
import com.android.autoservice.domain.model.User
// com.android.presentation.OrderViewModel
import com.android.autoservice.utils.UserType
import com.android.presentation.OrderViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import java.math.BigInteger
import java.security.MessageDigest

@AndroidEntryPoint
class RegistrationFragment : Fragment() {

    lateinit var viewBinding: FragmentRegistrationBinding
    private val USER_KEY = "User"
    private val dataBase = Firebase.database
    private val fireBaseRef = dataBase.getReference(USER_KEY)
    private val viewModel: OrderViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentRegistrationBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.btnRegister.setOnClickListener() {
            val name = viewBinding.etRegName.text.toString()
            val login = viewBinding.etRegLogin.text.toString()
            val password = viewBinding.etRegPassword.text.toString()
            if (!TextUtils.isEmpty(name)
                && !TextUtils.isEmpty(login)
                && !TextUtils.isEmpty(password)
            ) {
                val user = User(name, md5Transfer(password), UserType.CLIENT)
                fireBaseRef.child(login).setValue(user)
                Toast
                    .makeText(requireActivity(), "Регистрация прошла успешно", Toast.LENGTH_SHORT)
                    .show()
                view.findNavController()
                    .navigate(R.id.action_registrationFragment_to_authorizationFragment)
            } else {
                Toast
                    .makeText(requireActivity(), "Заполните все поля", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        viewBinding.btnToAuthFragment.setOnClickListener() {
            view.findNavController()
                .navigate(R.id.action_registrationFragment_to_authorizationFragment)
        }
    }

    fun md5Transfer(password:String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(password.toByteArray())).toString(16).padStart(32, '0')
    }
}