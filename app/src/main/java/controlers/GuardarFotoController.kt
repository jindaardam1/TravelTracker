package controlers


import android.content.Context
import utils.ConfirmarPais

class GuardarFotoController {
    fun getPhotoAndSaveOnDb(context: Context) {
        val confirmarPais = ConfirmarPais(context)

        confirmarPais.openCamera()

//        confirmarPais.
    }
}