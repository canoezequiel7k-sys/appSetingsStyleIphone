package com.arigondev.appsetting.bluetooth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class BluetoothViewModel(application: Application) : AndroidViewModel(application){

    private val repository = BluetoothRepository(application)
    private val _bluetoothEnable = MutableStateFlow(false)


    val bluetoothEnabled: StateFlow<Boolean> = _bluetoothEnable

    init {
        viewModelScope.launch {
            repository.bluetoothEnabledFlow.collectLatest { enabled ->
                _bluetoothEnable.value = enabled
            }
        }
    }

    fun toggleBluetooth(enabled: Boolean){
        viewModelScope.launch {
            repository.bluetoothSaveEnabled(enabled)
        }
    }




}