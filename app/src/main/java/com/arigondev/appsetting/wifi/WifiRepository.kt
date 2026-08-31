package com.arigondev.appsetting.wifi

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

//extencion para crear el DataStore
private val Context.dataStore by preferencesDataStore(name = "wifi_settings")

class WifiRepository(private val context: Context){
    private val gson = Gson()
    private val WIFI_LIST_KEY = stringPreferencesKey("wifi_list_key")

    private val WIFI_ENABLE_KEY = booleanPreferencesKey("wifi_enable_kwy")

    val wifiEnabledFlow: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[WIFI_ENABLE_KEY] ?: false
    }


    suspend fun saveWifiEnabled(enabled: Boolean){
        context.dataStore.edit {preferences ->
            preferences[WIFI_ENABLE_KEY] = enabled
        }
    }



    //Flujo (Flow): cada vez que los datos cambien, este flow emitira la nueva lista
    val wifiListFlow: Flow<List<WifiNetwork>> = context.dataStore.data.map { preferences ->
        val jsonString = preferences[WIFI_LIST_KEY] ?: ""
        if (jsonString.isEmpty()){
            emptyList()
        }else{
            val type = object : TypeToken<List<WifiNetwork>>() {}.type
            gson.fromJson(jsonString, type)
        }
    }

    //funcion asincrona para guardar
    suspend fun saveWifiList(network: List<WifiNetwork>){
        val jsonString = gson.toJson(network)
        context.dataStore.edit { preferences ->
            preferences[WIFI_LIST_KEY] = jsonString
        }
    }
}