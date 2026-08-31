package com.arigondev.appsetting.wifi

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.arigondev.appsetting.R
import com.arigondev.appsetting.databinding.ActivityDisplayWifiBinding
import com.arigondev.appsetting.databinding.DialogAddWifiBinding
import com.arigondev.appsetting.setupContainer
import kotlinx.coroutines.launch

class DisplayWifiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDisplayWifiBinding
    private lateinit var wifiAdapter: WifiAdapter // Declaración global
    private val viewModel: WifiViewModel by viewModels() // ViewModel delegado

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayWifiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        // Inicializamos el Adapter con una lista mutable vacía
        wifiAdapter = WifiAdapter(mutableListOf())

        // Configuramos el RecyclerView
        binding.rvNetworks.apply {
            adapter = wifiAdapter
            layoutManager = LinearLayoutManager(this@DisplayWifiActivity)
        }

        // 1. Escuchar los cambios en la lista de redes (Persistencia)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.networks.collect { list ->
                    wifiAdapter.updateData(list)
                }
            }
        }



        // 2. Configuración inicial del contenedor (Header del Wifi)
        binding.includedContainer.setupContainer(
            iconRes = R.drawable.ic_wifi,
            titleRes = R.string.wifi,
            descriptionRes = R.string.wifi_description,
            switchLabel = R.string.wifi,
            showSwitch = true
        )

        // 3. Lógica del Switch para mostrar/ocultar redes
        binding.includedContainer.switchFeature.setOnCheckedChangeListener { _, isChecked ->
            binding.layoutNetworks.visibility = if (isChecked) View.VISIBLE else View.GONE
        }

        // 4. Diálogo para AGREGAR Red (Botón Editar)
        binding.includedHeader.btnEdit.setOnClickListener {
            val dialogBinding = DialogAddWifiBinding.inflate(layoutInflater)
            AlertDialog.Builder(this)
                .setTitle("Nueva Red Wi-Fi")
                .setView(dialogBinding.root)
                .setPositiveButton("Agregar") { _, _ ->
                    val ssid = dialogBinding.etSsid.text.toString()
                    val pass = dialogBinding.etPassword.text.toString()
                    if (ssid.isNotEmpty()) {
                        viewModel.addNetwork(ssid, pass)
                    }
                }
                .setNegativeButton("Cancelar", null)
                .show()
        }

        // 5. Botón Atrás
        binding.includedHeader.btnBack.setOnClickListener {
            finish()
        }



        //observa el estado de guardado
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.wifiEnabled.collect { isEnabled ->
                    //actualiza el switch sin disparar el listener infinitamente si es necesario,
                    //o simplemente tetea el valor inicial
                    binding.includedContainer.switchFeature.isChecked = isEnabled
                }
            }
        }


        //Y actualiza el listener del switch
        binding.includedContainer.switchFeature.setOnCheckedChangeListener { _, isChecked ->
            binding.layoutNetworks.visibility = if (isChecked) View.VISIBLE else View.GONE
            viewModel.toggleWifi(isChecked) //Esto guarda el estado aca
        }


    }
}