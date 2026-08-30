package com.arigondev.appsetting

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.arigondev.appsetting.battery.DisplayBatteryActivity
import com.arigondev.appsetting.bluetooth.DisplayBluetoothActivity
import com.arigondev.appsetting.databinding.MainActivityBinding
import com.arigondev.appsetting.redmobile.DisplayReadMobileActivity
import com.arigondev.appsetting.wifi.DisplayWifiActivity


class MainActivity : AppCompatActivity() {


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
    }

    private fun initUI() {
        binding.btnAirMode.setOnClickListener {

        }
    }




}







