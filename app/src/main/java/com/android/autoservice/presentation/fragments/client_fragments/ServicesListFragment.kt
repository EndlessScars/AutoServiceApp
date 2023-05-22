package com.android.autoservice.presentation.fragments.client_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.android.autoservice.databinding.FragmentClientServicesListBinding
import com.android.autoservice.domain.model.Service
import com.android.autoservice.presentation.fragments.client_fragments.adapter.ClientServiceListAdapter
import com.android.autoservice.presentation.view_models.ServicesListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ServicesListFragment : Fragment() {

    private lateinit var viewBinding: FragmentClientServicesListBinding
    private lateinit var adapter: ClientServiceListAdapter
    private lateinit var recyclerView: RecyclerView
    private val viewModel: ServicesListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentClientServicesListBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.serviceListLiveData.observe(viewLifecycleOwner, Observer {
            initRecyclerView(it)
        })
    }

    private fun initRecyclerView(servicesList: List<Service>){
        recyclerView = viewBinding.rvServices
        adapter = ClientServiceListAdapter()
        recyclerView.adapter = adapter
        adapter.setList(servicesList)
    }
}