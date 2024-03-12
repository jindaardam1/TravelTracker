package utils

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.ByteArrayOutputStream

/**
 * Clase utilitaria para gestionar la confirmación de un país mediante la captura de fotos.
 *
 * @property context El contexto de la aplicación.
 */
class ConfirmarPais(private val context: Context) {
    private val CAMERA_REQUEST_CODE = 1003
    private val CAMERA_PERMISSION_REQUEST_CODE = 1004

    /**
     * Abre la cámara para capturar una foto del país.
     */
    fun openCamera() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            if (cameraIntent.resolveActivity(context.packageManager) != null) {
                (context as AppCompatActivity).startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
            }
        } else {
            ActivityCompat.requestPermissions(
                context as AppCompatActivity,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_REQUEST_CODE
            )
        }
    }

    /**
     * Método llamado en el método `onActivityResult` de la actividad para procesar la imagen capturada.
     *
     * @param requestCode El código de solicitud.
     * @param resultCode El código de resultado.
     * @param data La intención que contiene los datos de la imagen capturada.
     */
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK) {

            val imageBitmap = data?.extras?.get("data") as Bitmap

            val imageByteArray = bitmapToByteArray(imageBitmap)

            getPhotoToBits(imageByteArray)
        }
    }

    /**
     * Convierte un objeto [Bitmap] a un array de bytes ([ByteArray]).
     *
     * @param bitmap La imagen a convertir.
     * @return El array de bytes que representa la imagen.
     */
    private fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)

        return outputStream.toByteArray()
    }

    /**
     * Muestra la cantidad de bits de la foto y registra la cadena de bits en los registros.
     *
     * @param photoBytes El array de bytes que representa la imagen.
     */
    private fun getPhotoToBits(photoBytes: ByteArray) {
        Toast.makeText(context, "Cantidad de bits de la foto: " + photoBytes.size, Toast.LENGTH_LONG).show()

        var cadenaBits = ""

        photoBytes.forEach { bit ->
            cadenaBits += bit
        }

        Log.i("CadenaBitsFoto", cadenaBits)
    }
}
