package com.android.autoservice.presentation.fragments.client_fragments.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.autoservice.R
import com.android.autoservice.domain.model.Service
import kotlinx.android.synthetic.main.service_item.view.*

class ClientServiceListAdapter :
    RecyclerView.Adapter<ClientServiceListAdapter.ServiceListViewHolder>() {

    private var serviceList = listOf<Service>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.service_item,
            parent,
            false)
        return ServiceListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServiceListViewHolder, position: Int) {
        holder.itemView.tv_service_name.text = serviceList[position].name
        holder.itemView.tv_service_price.text = serviceList[position].price.toString()
    }

    override fun getItemCount(): Int {
        return serviceList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Service>){
        serviceList = list
        notifyDataSetChanged()
    }

    class ServiceListViewHolder(view: View) : RecyclerView.ViewHolder(view)
}