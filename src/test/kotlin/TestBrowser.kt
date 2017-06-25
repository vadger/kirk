import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.Browser.Companion.drive
import com.automation.remarks.kirk.BrowserConfig
import com.automation.remarks.kirk.text
import io.github.bonigarcia.wdm.ChromeDriverManager
import org.testng.Assert.assertEquals
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

/**
 * Created by sergey on 24.06.17.
 */
class TestBrowser {

    val url = "file:///home/sergey/Github/kirk/src/test/resources/start_page.html"

    @BeforeClass
    fun setUp() {
        ChromeDriverManager.getInstance().setup()
        Browser.conf(BrowserConfig().apply {
            screenSize = "640x480"
            forceQuit = false
        })
    }

    @Test
    fun testCanOpenUrl() {
        drive {
            to(url)
            assertEquals(currentUrl, url)
        }
    }

    @Test
    fun testCanOpenUrlUsingPage() {
        drive {
            to(url, ::StartPage)
            assertEquals(currentUrl, url)
        }
    }

    @Test
    fun testCanSetScreenSize() {
        drive {
            to(url)
        }
    }

    @Test
    fun testCanFindElement() {
        drive {
            to(url)
            s("#header").shouldHave(text("Selene"))
        }
    }

}

class StartPage