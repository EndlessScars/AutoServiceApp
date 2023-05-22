package com.android.autoservice.presentation.fragments.client_fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.android.autoservice.R
import com.android.autoservice.databinding.FragmentClientCreateOrderBinding
import com.android.autoservice.databinding.FragmentClientServicesListBinding
import com.android.autoservice.domain.model.Order
import com.android.autoservice.presentation.view_models.ClientCreateOrderViewModel
import com.android.autoservice.presentation.view_models.ServicesListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClientCreateOrderFragment : Fragment() {

    private lateinit var viewBinding: FragmentClientCreateOrderBinding
    private val viewModel: ClientCreateOrderViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentClientCreateOrderBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewBinding.btnCreateClientOrder.setOnClickListener(){
            val description = viewBinding.etClientOrderDescription.text.toString()
            if(!TextUtils.isEmpty(description)){
                val order: Order = Order()
                viewModel.createOrder()
            }
        }

    }
}