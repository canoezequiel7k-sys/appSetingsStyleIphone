package com.arigondev.appsetting.wifi

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class WifiViewModel(application: Application) : AndroidViewModel(application){
    private val repository = WifiRepository(application)
    private val _wifiEnable = MutableStateFlow(false)
    //Estado de la UI: las redes que el recyclerView debe mostrar
    private val _networks = MutableStateFlow<List<WifiNetwork>>(emptyList())
    val networks: StateFlow<List<WifiNetwork>> = _networks

    val wifiEnabled: StateFlow<Boolean> = _wifiEnable




    init {
        viewModelScope.launch {
            repository.wifiListFlow.collectLatest { list ->
                // Solo actualizamos la UI con lo que el Repositorio nos mande
                _networks.value = list
            }
        }
        //observar el estado del switch
        viewModelScope.launch {
            repository.wifiEnabledFlow.collectLatest { enabled ->
                _wifiEnable.value = enabled
            }
        }
    }


    fun toggleWifi(enabled: Boolean){
        viewModelScope.launch {
            repository.saveWifiEnabled(enabled)
        }
    }


    fun addNetwork(name: String, password: String){
        viewModelScope.launch {
            val newList = _networks.value.toMutableList()
            newList.add(WifiNetwork(
                ssid = name,
                hasPassword = password.isNotEmpty(),
                isConnected = false))

            repository.saveWifiList(newList)
        }
    }
}