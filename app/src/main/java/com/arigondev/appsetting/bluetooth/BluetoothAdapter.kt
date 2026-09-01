package com.arigondev.appsetting.bluetooth

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arigondev.appsetting.databinding.ItemBluetoothDeviceBinding

class BluetoothAdapter(private var devicesList: MutableList<BluetoothDevice>) :
    RecyclerView.Adapter<BluetoothViewHolder>() {

    // 1. Aquí reemplazamos ERROR por BluetoothViewHolder e inflamos el binding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BluetoothViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBluetoothDeviceBinding.inflate(layoutInflater, parent, false)
        return BluetoothViewHolder(binding)
    }

    // 2. Aquí conectamos la posición de la lista con el ViewHolder
    override fun onBindViewHolder(holder: BluetoothViewHolder, position: Int) {
        val device = devicesList[position]
        holder.bind(device)
    }

    override fun getItemCount(): Int = devicesList.size

    // 3. ¡No olvides esta función para poder actualizar la lista desde la Activity!
    fun updateData(newList: List<BluetoothDevice>) {
        this.devicesList = newList.toMutableList()
        notifyDataSetChanged()
    }
}