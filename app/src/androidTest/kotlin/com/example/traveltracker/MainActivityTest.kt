import android.content.Intent
import android.widget.ImageView
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.traveltracker.MainActivity
import com.example.traveltracker.MapaFragment
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import com.example.traveltracker.R


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private lateinit var activityScenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        // Inicializar el escenario de la actividad
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun testMainActivityNotNull() {
        // Verificar que la actividad no sea nula
        activityScenario.onActivity { activity ->
            assertNotNull(activity)
        }
    }

    @Test
    fun testLoadMapFragmentOnClickOption1() {
        // Simular el clic en la opción 1 de navegación
        activityScenario.onActivity { activity ->
            activity.findViewById<ImageView>(R.id.navigation_option1).performClick()
        }

        // Verificar que el fragmento cargado es MapaFragment
        activityScenario.onActivity { activity ->
            val currentFragment = activity.supportFragmentManager.findFragmentById(R.id.fragmentContainer)
            assertTrue(currentFragment is MapaFragment)
        }
    }


}
