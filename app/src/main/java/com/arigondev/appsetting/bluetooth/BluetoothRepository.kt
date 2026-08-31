package com.arigondev.appsetting.bluetooth

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


private val Context.dataStore by preferencesDataStore(name = "bluetooth_settings")

class BluetoothRepository(private val context: Context) {

    private val gson = Gson()

    //añadimos una clave
    private val BLUETOOTH_DEVICES_KEY = stringPreferencesKey("bluetooth_devices_key")

    //valores que necesitamos
    private val BLUETOOTH_ENABLED_KEY = booleanPreferencesKey("bluetooth_enabled_key")


    val bluetoothEnabledFlow: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[BLUETOOTH_ENABLED_KEY] ?: false
    }

    suspend fun bluetoothSaveEnabled(enabled: Boolean){
        context.dataStore.edit { preferences ->
            preferences[BLUETOOTH_ENABLED_KEY] = enabled }
    }
}