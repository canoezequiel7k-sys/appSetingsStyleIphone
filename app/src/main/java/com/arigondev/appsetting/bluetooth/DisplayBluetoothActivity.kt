package com.arigondev.appsetting.bluetooth

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.arigondev.appsetting.R
import com.arigondev.appsetting.databinding.ActivityDisplayBluetoothBinding
import com.arigondev.appsetting.setupContainer
import kotlinx.coroutines.launch

class DisplayBluetoothActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDisplayBluetoothBinding

    //aca declaro el viewModel
    private val viewModel: BluetoothViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //inicializamos el binding
        binding = ActivityDisplayBluetoothBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)

        binding.includedContainer.setupContainer(
            //Definimos el contenido
            iconRes = R.drawable.ic_bluetooth,
            titleRes = R.string.bluetooth,
            descriptionRes = R.string.bluetooth_description,
            switchLabel = R.string.bluetooth,
            showSwitch = true
        )

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.bluetoothEnabled.collect { isEnabled ->
                    //actualiza el switch sin disparar el listener infinitamente si es necesario,
                    //o simplemente tetea el valor inicial
                    if (binding.includedContainer.switchFeature.isChecked != isEnabled){
                        binding.includedContainer.switchFeature.isChecked = isEnabled
                    }
                }
            }
        }




        //observa el estado de guardado
        binding.includedHeader.apply {
            btnEdit.visibility = View.GONE //lo ocultamos al boton edit

            binding.includedHeader.btnBack.setOnClickListener {

                //el finish cierra la pantalla actual y vuelve a la anterior.
                finish()
            }
        }

        //Y actualiza el listener del switch
        binding.includedContainer.switchFeature.setOnCheckedChangeListener { _, isChecked ->
            viewModel.toggleBluetooth(isChecked)//aca guardo el estado del Switch del Bluetooth
        }



    }
}