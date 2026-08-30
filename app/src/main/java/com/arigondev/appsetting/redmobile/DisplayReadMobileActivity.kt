package com.arigondev.appsetting.redmobile

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.arigondev.appsetting.R
import com.arigondev.appsetting.databinding.ActivityDisplayReadMobileBinding
import com.arigondev.appsetting.setupContainer

class DisplayReadMobileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDisplayReadMobileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDisplayReadMobileBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)


        //binding llama los id que le pusimos a los include en el xml
        binding.includedContainer.setupContainer(iconRes = R.drawable.ic_red_mobile,
            titleRes = R.string.red_mobile,
            descriptionRes = R.string.redmobile_description,
            switchLabel = R.string.red_mobile,
            showSwitch = true)


        binding.includedHeader.btnBack.setOnClickListener {
            //el finish cierra la pantalla actual y vuelve a la anterior.
            finish()
        }
    }
}