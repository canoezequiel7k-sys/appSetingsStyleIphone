package com.arigondev.appsetting.redmobile

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.arigondev.appsetting.R
import com.arigondev.appsetting.databinding.ActivityDisplayReadMobileBinding
import com.arigondev.appsetting.setupInfoCard
import com.arigondev.appsetting.setupOptionsCard
import com.arigondev.appsetting.databinding.LayoutDialogStateBinding
import kotlinx.coroutines.launch

class DisplayReadMobileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDisplayReadMobileBinding
    private val viewModel: RedMobileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDisplayReadMobileBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)


        //binding llama los id que le pusimos a los include en el xml
        binding.includedContainer.setupInfoCard(
            iconRes = R.drawable.ic_red_mobile,
            titleRes = R.string.red_mobile,
            descriptionRes = R.string.redmobile_description
        )

        binding.includedStates.setupOptionsCard(
            switchLabelRes = R.string.red_mobile,
            optionsLabelRes = R.string.options,
            onSwitchChanged = { isEnabled ->
                //aca modificaremos mas tarde
                Log.d("RED_MOBILE", "Datos activados: $isEnabled")
                //enviamos las acciones del usuario al viewModel
                viewModel.setMobileDataEnabled(isEnabled)
            },
            onOptionsClick = {
                //creamos la instancia del Dialogo
                val dialog = Dialog(this)

                //inflamos el layout con el view binding
                val dialogBinding = LayoutDialogStateBinding.inflate(layoutInflater)
                dialog.setContentView(dialogBinding.root)

                //transparencia al fondo
                dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

                //escuchamos los cambios en el radioGroup
                dialogBinding.rgRoaming.setOnCheckedChangeListener { _, checkedOd ->
                    when(checkedOd){
                        R.id.rbOn -> {
                            //enviamos el dialoggo de roaming
                            Log.d("RED_MOBILE", "Seleccionó: Roaming Activado")
                            viewModel.setRoamingEnabled(true)
                            dialog.dismiss()
                        }
                        R.id.rbOff -> {
                            Log.d("RED_MOBILE", "Seleccionó: Roaming Desactivado")
                            viewModel.setRoamingEnabled(false)
                            dialog.dismiss()
                        }
                    }
                }

                dialog.show() }
        )



        binding.includedHeader.apply {
            btnEdit.visibility = View.GONE

            binding.includedHeader.btnBack.setOnClickListener {
                //el finish cierra la pantalla actual y vuelve a la anterior.
                finish()
            }
        }


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.isRoamingEnabled.collect { isEnabled ->
                    if (isEnabled){
                        binding.includedStates.tvRoamingValue.text = getString(R.string.featureStateOn)
                    }else{
                        binding.includedStates.tvRoamingValue.text = getString(R.string.featureStateOff)
                    }
                }
            }
        }

    }
}