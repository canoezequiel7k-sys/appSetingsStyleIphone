package com.arigondev.appsetting.battery

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.arigondev.appsetting.R
import com.arigondev.appsetting.databinding.ActivityDisplayBatteryBinding

class DisplayBatteryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDisplayBatteryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDisplayBatteryBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)


        binding.includedHeader.apply {
            //ponemos el titulo en el centrp
            tvCentralTitle.visibility = View.VISIBLE
            tvCentralTitle.text = getString(R.string.battery)


            //ocultamos el boton Editar
            btnEdit.visibility = View.GONE

            //Configuramos el boton de atras para que funcione
            btnBack.setOnClickListener { finish() }
        }
    }
}