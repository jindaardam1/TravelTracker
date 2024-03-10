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

class ConfirmarPais(private val context: Context) {
    private val CAMERA_REQUEST_CODE = 1003
    private val CAMERA_PERMISSION_REQUEST_CODE = 1004

    fun openCamera() {
        // Verificar si se tiene permiso para acceder a la cámara
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            // Crear un intent para abrir la aplicación de la cámara
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            // Verificar si hay una aplicación de cámara disponible para manejar el intent
            if (cameraIntent.resolveActivity(context.packageManager) != null) {
                // Iniciar la aplicación de la cámara con el intent
                (context as AppCompatActivity).startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
            }
        } else {
            // Solicitar permiso de la cámara si no se tiene
            ActivityCompat.requestPermissions(
                context as AppCompatActivity,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_REQUEST_CODE
            )
        }
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK) {
            // Obtener la imagen capturada como un Bitmap
            val imageBitmap = data?.extras?.get("data") as Bitmap
            // Convertir el Bitmap a un ByteArray
            val imageByteArray = bitmapToByteArray(imageBitmap)
            // Pasar el ByteArray al método getPhotoToBits()
            getPhotoToBits(imageByteArray)
        }
    }

    private fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        // Comprimir el bitmap en formato JPEG con calidad 100 y escribirlo en un OutputStream
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        // Obtener el ByteArray del OutputStream
        return outputStream.toByteArray()
    }

    private fun getPhotoToBits(photoBytes: ByteArray) {
        Toast.makeText(context, "Cantidad de bits de la foto: " + photoBytes.size, Toast.LENGTH_LONG).show()

        var cadenaBits = ""

        photoBytes.forEach { bit ->
            cadenaBits += bit
        }

        Log.i("CadenaBitsFoto", cadenaBits)
    }
}
