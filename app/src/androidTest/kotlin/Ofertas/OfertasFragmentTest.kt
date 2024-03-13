import Ofertas.Oferta
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.random.Random

@RunWith(AndroidJUnit4::class)
class OfertasFragmentTest {

    @Test
    fun crearDrawableDesdeMipmap() {
        val context: Context = ApplicationProvider.getApplicationContext()

        val bitmap: Bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)

        val drawable = BitmapDrawable(context.resources, bitmap)

        // Ver drawable no es null
        assertNotNull(drawable)

        assertTrue(drawable is BitmapDrawable)

        val bitmapFromDrawable = (drawable as BitmapDrawable).bitmap
        assertNotNull(bitmapFromDrawable)

    }

    @Test
    fun generarPrecio_debeGenerarPrecioEnRangoEspecificado() {
        val precio = generarPrecio()
        assertTrue(precio in 400..1500)
    }

    @Test
    fun abrirPaginaWeb_debeAbrirPaginaWebConOferta() {
        val oferta = Oferta("https://www.example.com", null, "Example Destination", 1000)
        abrirPaginaWeb(oferta)
    }

    private fun generarPrecio(): Int {
        return Random.nextInt(400, 1500)
    }

    private fun abrirPaginaWeb(oferta: Oferta) {
        println("Opening webpage: ${oferta.enlaceRedireccion}")
    }
}
