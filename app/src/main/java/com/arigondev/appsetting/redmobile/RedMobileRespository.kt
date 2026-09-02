package com.arigondev.appsetting.redmobile

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


//creamos la base de datos con DataStore para la red movil
private val Context.dataStore by preferencesDataStore(name = "red_mobile_settings")

class RedMobileRespository(private val context: Context) {
    //definimos las claves
    private val MOBILE_DATA_KEY = booleanPreferencesKey("mobile_data_key")
    private val ROAMING_KEY = booleanPreferencesKey("roaming_key")
    private val LINE_ACTIVATED_KEY = booleanPreferencesKey("line_activated_key")


    //leer los datos(Flows) Por defecto activado
    val mobileDataFlow: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[MOBILE_DATA_KEY] ?: true
    }
    val roamingFlow: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[ROAMING_KEY] ?: true
    }
    val lineActivatedFlow: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[LINE_ACTIVATED_KEY] ?: true
    }

    suspend fun saveMobileData(enabled : Boolean){
        context.dataStore.edit { preferences ->
            preferences[MOBILE_DATA_KEY] = enabled
        }
    }

    suspend fun saveRoaming(enabled: Boolean){
        context.dataStore.edit { preferences ->
            preferences[ROAMING_KEY] = enabled
        }
    }

    suspend fun saveLineActivated(enabled: Boolean){
        context.dataStore.edit { preferences ->
            preferences[LINE_ACTIVATED_KEY] = enabled
        }
    }
}