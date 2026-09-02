package com.arigondev.appsetting

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.arigondev.appsetting.battery.DisplayBatteryActivity
import com.arigondev.appsetting.bluetooth.DisplayBluetoothActivity
import com.arigondev.appsetting.databinding.MainActivityBinding
import com.arigondev.appsetting.redmobile.DisplayReadMobileActivity
import com.arigondev.appsetting.wifi.DisplayWifiActivity
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {


    private val themeViewModel: ThemeViewModel by viewModels()
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()
        initUI()
    }

    private fun initListeners() {
        binding.btnWifi.setOnClickListener {
            val intent =  Intent(this, DisplayWifiActivity::class.java)
            startActivity(intent)
        }
        binding.btnBluetooth.setOnClickListener {
            val intent = Intent(this, DisplayBluetoothActivity::class.java)
            startActivity(intent)
        }
        binding.btnRedMobile.setOnClickListener {
            val intent = Intent(this, DisplayReadMobileActivity::class.java)
            startActivity(intent)
        }
        binding.btnBatery.setOnClickListener {
            val intent = Intent(this, DisplayBatteryActivity::class.java)
            startActivity(intent)
        }
        binding.switchDarkMode.setOnCheckedChangeListener{ _, isChecked ->
            //isPressed evita un bucle infinito si el cambio vino del Flow y no del dedo del usuario
            if (binding.switchDarkMode.isPressed) {
                themeViewModel.setDarkModeEnabled(isChecked)
            }
        }
    }

    private fun initUI() {
        binding.btnAirMode.setOnClickListener {
        }

        lifecycleScope.launch {
            themeViewModel.isDarkModeEnabled.collect { isEnabled ->
                //sincronizamos el switch visualmente
                binding.switchDarkMode.isChecked = isEnabled

                //aplicar el modo en la app con AppCompatDelegate
                if (isEnabled){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        }
    }




}







