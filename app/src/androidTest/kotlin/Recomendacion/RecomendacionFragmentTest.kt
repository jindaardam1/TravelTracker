import android.content.Context
import Recomendacion.RecomendacionFragment
import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mockito.mock
import java.lang.reflect.Field
import androidx.fragment.app.testing.launchFragmentInContainer


class RecomendacionFragmentTest {

    private lateinit var context: Context

    private fun setFragmentContext(fragment: RecomendacionFragment, context: Context) {
        try {
            val field: Field = RecomendacionFragment::class.java.getDeclaredField("mContext")
            field.isAccessible = true
            field.set(fragment, context)
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
    }
    @Test
    fun testFragmentNotNull() {
        // Inicializar el contexto simulado utilizando Mockito
        context = mock(Context::class.java)
        val fragment = RecomendacionFragment()
        setFragmentContext(fragment, context)
        assertNotNull(fragment)
    }

    @Test
    fun testGenerarSitiosInteres_ContieneRestaurante() {
        context = mock(Context::class.java)

        val fragment = RecomendacionFragment()

        setFragmentContext(fragment, context)

        val sitiosInteres = fragment.generarSitiosInteres()

        assertTrue(sitiosInteres.isNotEmpty())

        // Verificar que al menos un elemento de la lista contiene la palabra "Restaurante" en su nombre
        val contieneRestaurante = sitiosInteres.any { sitio -> sitio.nombre.contains("Restaurante") }
        assertTrue("La lista tiene al menos un restaurante", contieneRestaurante)
    }


}
