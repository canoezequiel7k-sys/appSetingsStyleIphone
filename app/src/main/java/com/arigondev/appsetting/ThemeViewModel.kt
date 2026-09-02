package com.arigondev.appsetting

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ThemeViewModel(application: Application): AndroidViewModel(application) {

    private val repository = ThemeRepository(application)
    private val _isDarkModeEnabled = MutableStateFlow(false)

    val isDarkModeEnabled: StateFlow<Boolean> = _isDarkModeEnabled.asStateFlow()


    init {
        viewModelScope.launch {
            repository.dmEnabledFlow.collectLatest { enabled ->
                _isDarkModeEnabled.value = enabled
            }
        }
    }

    //funcion para cambiar el tema:
    fun setDarkModeEnabled(enabled : Boolean){
        viewModelScope.launch {
            repository.saveDarkMode(enabled)
        }
    }
}