package com.arigondev.appsetting.bluetooth

import androidx.recyclerview.widget.RecyclerView
import com.arigondev.appsetting.databinding.ItemBluetoothDeviceBinding


class BluetoothViewHolder(private val binding: ItemBluetoothDeviceBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(device: BluetoothDevice) {
        binding.nameDevice.text = device.name
        binding.StateDevice.text = device.status
    }
}