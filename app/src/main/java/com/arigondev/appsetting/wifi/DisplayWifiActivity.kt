package com.arigondev.appsetting.wifi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.arigondev.appsetting.R
import com.arigondev.appsetting.databinding.ActivityDisplayWifiBinding
import com.arigondev.appsetting.setupContainer

class DisplayWifiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDisplayWifiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Inicializamos el binding
        binding = ActivityDisplayWifiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        //Usamos el id del "includedContainer" que pusimos en el xml
        binding.includedContainer.setupContainer(
            iconRes = R.drawable.ic_wifi,
            titleRes = R.string.wifi,
            descriptionRes = R.string.wifi_description,
            switchLabel = R.string.wifi,
            showSwitch = true)

        //configuramos el boton de atras del header para que funcione
        binding.includedHeader.btnBack.setOnClickListener {
            //el finish cierra la pantalla actual y vuelve a la anterior.
            finish()
        }

    }
}