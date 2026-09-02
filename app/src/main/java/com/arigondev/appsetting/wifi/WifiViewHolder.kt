package com.arigondev.appsetting.wifi

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.arigondev.appsetting.R
import com.arigondev.appsetting.databinding.ItemWifiNetworkBinding

class WifiViewHolder(private val binding: ItemWifiNetworkBinding) :
    RecyclerView.ViewHolder(binding.root) {

    /**
     * Esta función vincula los datos de una red con los elementos visuales de la fila.
     */
    fun bind(network: WifiNetwork) {
        val context = binding.root.context

        binding.apply {
            // Asignamos el nombre de la red
            tvSsid.text = network.ssid

            // Mostramos el check azul solo si está conectada
            ivCheck.visibility = if (network.isConnected) View.VISIBLE else View.INVISIBLE

            // Mostramos el candado solo si tiene contraseña (usamos GONE para no ocupar espacio)
            ivLock.visibility = if (network.hasPassword) View.VISIBLE else View.GONE

            // Asignamos el icono de señal Wi-Fi
            ivSignal.setImageResource(R.drawable.ic_wifi_redes)

            // Configuramos el color del nombre de la red
            // (Si está conectada podrías ponerlo en un azul iOS más adelante)
            tvSsid.setTextColor(context.getColor(R.color.text_primary))

            // Listener para el icono de información 'i'
            ivInfo.setOnClickListener {
                // Aquí podrías abrir un diálogo con detalles de la red en el futuro
            }
        }
    }
}