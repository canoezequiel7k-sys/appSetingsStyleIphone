package com.arigondev.appsetting.wifi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arigondev.appsetting.databinding.ItemWifiNetworkBinding

class WifiAdapter(private var networkList: MutableList<WifiNetwork>) :
    RecyclerView.Adapter<WifiViewHolder>() {

    fun updateData(newList: List<WifiNetwork>) {
        // En lugar de limpiar la lista vieja, la reemplazamos por una copia mutable de la nueva
        this.networkList = newList.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WifiViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemWifiNetworkBinding.inflate(layoutInflater, parent, false)
        return WifiViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WifiViewHolder, position: Int) {
        val network = networkList[position]
        holder.bind(network)
    }

    override fun getItemCount(): Int = networkList.size
}