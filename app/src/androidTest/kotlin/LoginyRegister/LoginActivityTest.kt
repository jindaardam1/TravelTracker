import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.traveltracker.MainActivity
import com.example.traveltracker.R
import org.junit.Rule
import org.junit.Test

class LoginActivityTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loginActivityTest() {
        // Espera a que se cargue la actividad de inicio de sesión
        onView(withId(R.id.editTextUsername)).check(matches(isDisplayed()))

        // Ingresa el nombre de usuario y la contraseña y hace clic en el botón de inicio de sesión
        onView(withId(R.id.editTextUsername)).perform(replaceText("midgard"), closeSoftKeyboard())
        onView(withId(R.id.editTextPassword)).perform(replaceText("xx"), closeSoftKeyboard())
        onView(withId(R.id.buttonLogin)).perform(click())

        // Espera a que se abra la siguiente actividad (supongamos que es la actividad del perfil)
        onView(withId(R.id.navigation_option4)).check(matches(isDisplayed()))
    }
}
