package com.arigondev.appsetting.wifi

data class WifiNetwork(
    val ssid: String,    //Nombre de la RED
    val hasPassword: Boolean,    //tiene candado?
    val isConnected: Boolean,     //Es la red actual?
    val signalStrength: Int = 3 //1 a 4
)
