package com.arigondev.appsetting.bluetooth

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.arigondev.appsetting.R
import com.arigondev.appsetting.databinding.ActivityDisplayBluetoothBinding
import com.arigondev.appsetting.setupContainer
import kotlinx.coroutines.launch

class DisplayBluetoothActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDisplayBluetoothBinding
    private lateinit var bluetoothAdapter: BluetoothAdapter
    private val viewModel: BluetoothViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDisplayBluetoothBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        binding.includedContainer.setupContainer(
            iconRes = R.drawable.ic_bluetooth,
            titleRes = R.string.bluetooth,
            descriptionRes = R.string.bluetooth_description,
            switchLabel = R.string.bluetooth,
            showSwitch = true
        )

        // Configurar RecyclerView
        bluetoothAdapter = BluetoothAdapter(mutableListOf())
        binding.rvBluetoothDevices.apply {
            adapter = bluetoothAdapter
            layoutManager = LinearLayoutManager(this@DisplayBluetoothActivity)
        }

        // Observar estado Switch y Visibilidad
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.bluetoothEnabled.collect { isEnabled ->
                    if (binding.includedContainer.switchFeature.isChecked != isEnabled) {
                        binding.includedContainer.switchFeature.isChecked = isEnabled
                    }
                    binding.layoutDevicesContainer.visibility = if (isEnabled) View.VISIBLE else View.GONE
                }
            }
        }

        // Observar lista de dispositivos
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.devices.collect { list ->
                    bluetoothAdapter.updateData(list)
                }
            }
        }

        binding.includedHeader.apply {
            btnEdit.visibility = View.GONE
            btnBack.setOnClickListener { finish() }
        }

        binding.includedContainer.switchFeature.setOnCheckedChangeListener { _, isChecked ->
            viewModel.toggleBluetooth(isChecked)
        }

        // CARGA DE PRUEBA: Si la lista está vacía, añade estos 3 para que veas que funciona
        if (viewModel.devices.value.isEmpty()) {
            viewModel.addDevice("SOUND9PRO", "No conectado")
            viewModel.addDevice("TWS", "No conectado")
            viewModel.addDevice("WI-C100", "No conectado")
            viewModel.addDevice("SOUND9PRO", "No conectado")
        }
    }
}