package com.arigondev.appsetting

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


private val Context.dataStore by preferencesDataStore(name = "theme_settings")

class ThemeRepository(private val context: Context) {

    //añadimos la clave
    private val DARK_MODE_ENABLE = booleanPreferencesKey("dark_mode_enable")

    //Leer el estado del modo oscuro
    val dmEnabledFlow: Flow<Boolean> = context.dataStore.data.map{ preferences ->
        preferences[DARK_MODE_ENABLE] ?: false
    }

    //Guardamos la eleccion del usuario
    suspend fun saveDarkMode(enabled: Boolean){
        context.dataStore.edit { preferences ->
            preferences[DARK_MODE_ENABLE] = enabled }
    }
}