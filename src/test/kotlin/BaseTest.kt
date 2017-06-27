import helpers.JettyServer
import io.github.bonigarcia.wdm.ChromeDriverManager
import io.github.bonigarcia.wdm.FirefoxDriverManager
import org.openqa.selenium.net.PortProber
import org.testng.annotations.BeforeSuite

/**
 * Created by sepi on 6/27/2017.
 */
abstract class BaseTest {

    val port = PortProber.findFreePort()

    @BeforeSuite
    fun runServer() {
        JettyServer(port).runServer()
        ChromeDriverManager.getInstance().setup()
        FirefoxDriverManager.getInstance().setup()
    }

    val url: String = "http://localhost:$port/"
}