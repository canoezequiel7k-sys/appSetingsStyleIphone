# persistencia de estado para WiFi y Bluetooth

El problema es que el estado de los interruptores (switches) de WiFi y Bluetooth no se está guardando en una persistencia (como DataStore). Cada vez que la actividad se recrea, vuelve a su estado inicial por defecto.

## Cambios propuestos

### WiFi Component

#### [MODIFY] [WifiRepository.kt](file:///C:/Users/SystemAncestral/AndroidStudioProjects/appSetting/app/src/main/java/com/arigondev/appsetting/wifi/WifiRepository.kt)
- Añadir una clave `WIFI_ENABLED_KEY` en DataStore.
- Crear un `wifiEnabledFlow` para observar el estado del interruptor.
- Crear una función `saveWifiEnabled(Boolean)` para persistir el estado.

#### [MODIFY] [WifiViewModel.kt](file:///C:/Users/SystemAncestral/AndroidStudioProjects/appSetting/app/src/main/java/com/arigondev/appsetting/wifi/WifiViewModel.kt)
- Exponer un `StateFlow<Boolean>` con el estado de WiFi cargado desde el repositorio.
- Añadir una función `toggleWifi(Boolean)` para actualizar el repositorio.

#### [MODIFY] [DisplayWifiActivity.kt](file:///C:/Users/SystemAncestral/AndroidStudioProjects/appSetting/app/src/main/java/com/arigondev/appsetting/wifi/DisplayWifiActivity.kt)
- Observar `wifiEnabled` desde el ViewModel para marcar el Switch al iniciar.
- Llamar a `viewModel.toggleWifi(isChecked)` cuando el usuario cambie el Switch.

---

### Bluetooth Component

#### [NEW] [BluetoothRepository.kt](file:///C:/Users/SystemAncestral/AndroidStudioProjects/appSetting/app/src/main/java/com/arigondev/appsetting/bluetooth/BluetoothRepository.kt)
- Crear un repositorio similar al de WiFi para guardar el estado `BLUETOOTH_ENABLED`.

#### [NEW] [BluetoothViewModel.kt](file:///C:/Users/SystemAncestral/AndroidStudioProjects/appSetting/app/src/main/java/com/arigondev/appsetting/bluetooth/BluetoothViewModel.kt)
- Crear un ViewModel para manejar la lógica de Bluetooth.

#### [MODIFY] [DisplayBluetoothActivity.kt](file:///C:/Users/SystemAncestral/AndroidStudioProjects/appSetting/app/src/main/java/com/arigondev/appsetting/bluetooth/DisplayBluetoothActivity.kt)
- Integrar el ViewModel y observar/actualizar el estado del interruptor de Bluetooth.

## Plan de Verificación

### Pruebas Manuales
1. Abrir la pantalla de WiFi, activar el switch, volver atrás y entrar de nuevo. El switch debe seguir activado.
2. Hacer lo mismo con Bluetooth.
3. Reiniciar la aplicación y verificar que los estados se mantienen.
