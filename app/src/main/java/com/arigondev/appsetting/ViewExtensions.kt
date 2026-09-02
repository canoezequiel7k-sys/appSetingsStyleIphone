package com.arigondev.appsetting

import android.view.View
import com.arigondev.appsetting.databinding.LayoutCustomContainerBinding
import com.arigondev.appsetting.databinding.LayoutInfoCardRedMobileBinding
import com.arigondev.appsetting.databinding.LayoutSettingRowRedMobileBinding

/**
 * Función de extensión para personalizar el contenedor de ajustes.
 *
 * @param iconRes ID del recurso de imagen (R.drawable.xxx)
 * @param titleRes ID del recurso de texto para el título (R.string.xxx)
 * @param descriptionRes ID opcional para la descripción. Si es null, se oculta.
 * @param switchLabelRes ID opcional para el texto junto al switch.
 * @param showSwitch Booleano que determina si se muestra la fila del switch y el divisor.
 */


//Funcion de extencion para el binding de los contenedores
fun LayoutCustomContainerBinding.setupContainer(
    iconRes: Int,
    titleRes: Int,
    descriptionRes: Int? = null,
    switchLabel: Int? = null,
    showSwitch: Boolean = true
){
    //Obtenemos el contexto de la vista raiz del binding
    val context = root.context

    //Usamos el id ivFeatureIcon y le asignamos un valor
    ivFeatureIcon.setImageResource(iconRes)

    //Traducimos el id de texto y lo asignamos al TextView
    tvFeatureTitle.text = context.getString(titleRes)

    //Logica para la descripcion
    if (descriptionRes != null){
        //Si el usuario paso por ID, buscamos el texto y mostramos el TextView
        tvFeatureDescription.text = context.getString(descriptionRes)
        tvFeatureDescription.visibility = View.VISIBLE
    }else{
        //si el parametro es null, ocultamos el TextView por completo(GONE)
        tvFeatureDescription.visibility = View.GONE
    }


    //Logica para Switch y el divisor(Linea gris)
    if (showSwitch && switchLabel != null) {
        boxTwo.visibility = View.VISIBLE
        divider.visibility = View.VISIBLE
        tvSwitchLabel.text = context.getString(switchLabel)
    } else {
        // Si no se necesita el switch (ej. pantalla de batería),
        // ocultamos tanto la fila (boxTwo) como el divisor.
        boxTwo.visibility = View.GONE
        divider.visibility = View.GONE
    }
}

/*---------------------------------------------------------------------------------
*                                                                                 |
*                         FUNCIONES PARA RED MOBILE                               |
*                                                                                 |
* --------------------------------------------------------------------------------*/


//Esta funcion va a servir para poner el icono, la descripcion y el titulo
fun LayoutInfoCardRedMobileBinding.setupInfoCard(
    iconRes: Int,
    titleRes: Int,
    descriptionRes: Int? = null
){
    val context = root.context
    ivFeatureIcon.setImageResource(iconRes)
    tvFeatureTitle.text = context.getString(titleRes)

    if (descriptionRes != null){
        tvFeatureDescription.text = context.getString(descriptionRes)
        tvFeatureDescription.visibility = android.view.View.VISIBLE
    }else{
        tvFeatureDescription.visibility = android.view.View.GONE
    }
}

//Esta es más interesante porque maneja dos cosas a la vez dentro de la misma tarjeta.
fun LayoutSettingRowRedMobileBinding.setupOptionsCard(
    switchLabelRes: Int,
    optionsLabelRes: Int,
    onSwitchChanged: (Boolean) -> Unit,
    onOptionsClick:() -> Unit
){
    val context = root.context

    //configura la primer fila (La del switch)
    tvFeatureTitle.text = context.getString(switchLabelRes)

    switchRedMobile.setOnCheckedChangeListener { _, isChecked ->
        onSwitchChanged(isChecked)}

        //configura la segunda fiula (La de Opciones)
    tvFeatureState.text = context.getString(optionsLabelRes)
    optionState.setOnClickListener {
        onOptionsClick()
    }

}