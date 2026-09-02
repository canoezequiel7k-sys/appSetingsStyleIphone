package com.arigondev.appsetting.redmobile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

//estado privado (mutable)


class RedMobileViewModel : ViewModel() {
    //Aqui pondremos los estamos de roaming y datos celulares
    private val _isRoamingEnabled = MutableStateFlow(true) //empieza activado por defecto
    private val _isMobileDataEnabled = MutableStateFlow(true)

    //estado publico(lectura) solo el viewModel puede modificarlo
    val isRoamingEnabled : StateFlow<Boolean> = _isRoamingEnabled.asStateFlow()
    val isMobileDataEnabled: StateFlow<Boolean> = _isMobileDataEnabled.asStateFlow()


    fun setRoamingEnabled(enabled: Boolean){
        _isRoamingEnabled.value = enabled
    }

    fun setMobileDataEnabled(enabled: Boolean){
        _isMobileDataEnabled.value = enabled
    }
}