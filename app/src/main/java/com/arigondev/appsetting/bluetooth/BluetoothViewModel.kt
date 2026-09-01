package com.arigondev.appsetting.bluetooth

import kotlin.collections.emptyList
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class BluetoothViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = BluetoothRepository(application)
    private val _bluetoothEnable = MutableStateFlow(false)
    private val _devices = MutableStateFlow<List<BluetoothDevice>>(emptyList())

    val devices: StateFlow<List<BluetoothDevice>> = _devices
    val bluetoothEnabled: StateFlow<Boolean> = _bluetoothEnable

    init {
        viewModelScope.launch {
            repository.bluetoothEnabledFlow.collectLatest { enabled ->
                _bluetoothEnable.value = enabled
            }
        }
        viewModelScope.launch {
            repository.bluetoothListFlow.collectLatest { list ->
                _devices.value = list
            }
        }
    }

    fun addDevice(name: String, status: String) {
        viewModelScope.launch {
            val currentList = _devices.value.toMutableList()
            currentList.add(BluetoothDevice(name, status, id = System.currentTimeMillis().toString()))
            repository.saveBluetoothDevices(currentList)
        }
    }

    fun toggleBluetooth(enabled: Boolean) {
        viewModelScope.launch {
            repository.bluetoothSaveEnabled(enabled)
        }
    }
}