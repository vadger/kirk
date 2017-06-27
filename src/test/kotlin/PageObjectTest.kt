import com.automation.remarks.kirk.conditions.be
import com.automation.remarks.kirk.Browser
import io.github.bonigarcia.wdm.ChromeDriverManager
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.Test
import pages.StartPage

/**
 * Created by sergey on 26.06.17.
 */
class PageObjectTest : BaseTest() {

    @Test
    fun testCanOpenUrl() {
        ChromeDriverManager.getInstance().setup()
        Browser.drive {
            to(::StartPage)
                    .fillForm("This is test")
        }

        Browser(ChromeDriver())
                .element("#header").should(be.visible)
    }
}