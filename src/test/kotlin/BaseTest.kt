
import helpers.JettyServer
import io.github.bonigarcia.wdm.ChromeDriverManager
import me.tatarka.assertk.assertions.hasClass
import org.openqa.selenium.net.PortProber
import org.testng.annotations.BeforeSuite
import kotlin.reflect.KClass

/**
 * Created by sepi on 6/27/2017.
 */
abstract class BaseTest {

    lateinit var url: String

    @BeforeSuite
    fun runServer() {
        val port = PortProber.findFreePort()
        JettyServer(port).runServer()
        ChromeDriverManager.getInstance().setup()
        url = "http://localhost:$port/"
    }


    fun <T : Any> assertExceptionThrown(kclass: KClass<out T>, closure: () -> Unit) {
        me.tatarka.assertk.assert {
            closure()
        }.throwsError {
            it.hasClass(kclass)
            print(it.actual.message)
        }
    }
}