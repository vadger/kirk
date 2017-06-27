import com.automation.remarks.kirk.Browser.Companion.drive
import com.automation.remarks.kirk.be
import com.automation.remarks.kirk.have
import io.github.bonigarcia.wdm.ChromeDriverManager
import io.github.bonigarcia.wdm.FirefoxDriverManager
import me.tatarka.assertk.assert
import me.tatarka.assertk.assertions.hasClass
import org.openqa.selenium.TimeoutException
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
        FirefoxDriverManager.getInstance().setup()
    }

    @Test
    fun testCanOpenUrl() {
        drive {
            to(url)
            assertEquals(currentUrl, url)
        }
    }

    @Test
    fun testCanFindElement() {
        drive {
            to(url)
            element("#header").should(have.text("Selene"))
            element("#input").setVal("This is test")
            element("#input").should(be.visible)
        }
    }

    @Test
    fun testShouldThrowTimeOutException() {
        assert {
            drive {
                to(url)
                element("#header").should(have.text("Slene"))
            }
        }.throwsError {
            it.hasClass(TimeoutException::class)
        }
    }
}