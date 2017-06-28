
import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.Config
import io.github.bonigarcia.wdm.ChromeDriverManager
import org.testng.annotations.Test
import pages.StartPage

/**
 * Created by sergey on 26.06.17.
 */
class PageObjectTest : BaseTest() {

    @Test
    fun testCanOpenUrl() {
        ChromeDriverManager.getInstance().setup()
        val config = Config(baseUrl = url)
        Browser.drive(config = config) {
            to(::StartPage)
                    .fillForm("This is test")
        }
    }
}