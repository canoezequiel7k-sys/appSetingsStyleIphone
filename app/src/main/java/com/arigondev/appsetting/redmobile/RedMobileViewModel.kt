package com.arigondev.appsetting.redmobile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch




//usamos (application) porque Nos regala acceso al Application que es un contexto global
class RedMobileViewModel(application: Application): AndroidViewModel(application) {
    //Instanciamos el repositorio
    private val repository = RedMobileRespository(application)
    //Aqui pondremos los estamos de roaming y datos celulares
    private val _isRoamingEnabled = MutableStateFlow(true) //empieza activado por defecto
    private val _isMobileDataEnabled = MutableStateFlow(true)
    private val _isLineActivated = MutableStateFlow(true)


    //estado publico(lectura) solo el viewModel puede modificarlo
    val isRoamingEnabled : StateFlow<Boolean> = _isRoamingEnabled.asStateFlow()
    val isMobileDataEnabled: StateFlow<Boolean> = _isMobileDataEnabled.asStateFlow()
    val isLineActivated: StateFlow<Boolean> = _isLineActivated.asStateFlow()


    init {
        //Escuchamos si hay cambios en Roaming guardados en el disco
        viewModelScope.launch {
            repository.roamingFlow.collectLatest { enabled ->
                _isRoamingEnabled.value = enabled
            }
        }
        //Escuchamos si hay cambios en Datos Celulares guardados en el disco
        viewModelScope.launch {
            repository.mobileDataFlow.collectLatest { enabled ->
                _isMobileDataEnabled.value = enabled
            }
        }
        //escuchamos si hay cambios en linestate
        viewModelScope.launch {
            repository.lineActivatedFlow.collectLatest { enabled ->
                _isLineActivated.value = enabled
            }
        }
    }


    fun setRoamingEnabled(enabled: Boolean){
        viewModelScope.launch {
            repository.saveRoaming(enabled)
        }
    }

    fun setMobileDataEnabled(enabled: Boolean){
        viewModelScope.launch {
            repository.saveMobileData(enabled)
        }
    }

    fun setLineActivated(enabled: Boolean){
        viewModelScope.launch {
            repository.saveLineActivated(enabled)
        }
    }
}