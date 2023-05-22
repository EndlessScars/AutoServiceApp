package com.android.autoservice.presentation.fragments.client_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.android.autoservice.R
import com.android.autoservice.databinding.FragmentAuthorizationBinding
import com.android.autoservice.databinding.FragmentClientBinding


class ClientFragment : Fragment() {
    private lateinit var viewBinding: FragmentClientBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentClientBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.btnClientServicesList.setOnClickListener() {
            view.findNavController()
                .navigate(R.id.action_clientFragment_to_servicesListFragment)
        }
    }

}