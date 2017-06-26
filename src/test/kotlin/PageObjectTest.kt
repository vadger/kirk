import com.automation.remarks.kirk.Browser
import io.github.bonigarcia.wdm.ChromeDriverManager
import org.testng.annotations.Test

/**
 * Created by sergey on 26.06.17.
 */
class PageObjectTest {

    @Test
    fun testCanOpenUrl() {
        ChromeDriverManager.getInstance().setup()
        Browser.drive {
            to(::StartPage)
                    .fillForm("This is test")
        }
    }
}