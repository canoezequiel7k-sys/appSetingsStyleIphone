package com.arigondev.appsetting.bluetooth

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.arigondev.appsetting.R
import com.arigondev.appsetting.databinding.ActivityDisplayBluetoothBinding
import com.arigondev.appsetting.setupContainer

class DisplayBluetoothActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDisplayBluetoothBinding

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

        binding.includedHeader.apply {
            btnEdit.visibility = View.GONE //lo ocultamos al boton edit

            binding.includedHeader.btnBack.setOnClickListener {

                //el finish cierra la pantalla actual y vuelve a la anterior.
                finish()
            }
        }


    }
}