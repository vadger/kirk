import com.automation.remarks.kirk.Browser.Companion.drive
import com.automation.remarks.kirk.conditions.be
import com.automation.remarks.kirk.conditions.have
import org.openqa.selenium.TimeoutException
import org.testng.Assert.assertEquals
import org.testng.annotations.Test

/**
 * Created by sergey on 24.06.17.
 */
class TestBrowser : BaseTest() {

    @Test
    fun testCanOpenUrl() {
        drive {
            val currentUrl = to(url)
            assertEquals(currentUrl, url)
        }
    }

    @Test
    fun testCanFindElement() {
        drive {
            to(url)
            element("#header").should(have.text("Selene"))
            element("#input").should(be.visible)
        }
    }

    @Test
    fun testCanSetValue() {
        drive {
            to(url)
            element("#input").setVal("This is test")
        }
    }

    @Test
    fun testShouldThrowTimeOutException() {
        assertExceptionThrown(TimeoutException::class) {
            drive {
                to(url)
                element("#header").should(have.text("Slene"))
            }
        }
    }

    @Test
    fun testShouldThrowExceptionAtInvisibleElement() {
        assertExceptionThrown(TimeoutException::class) {
            drive {
                to(url)
                element("#input_invisible").setVal("demo")
            }
        }
    }

    @Test
    fun testShouldFailOnWrongSelector() {
        drive {
            to(url)
            element("#wrongLocator").should(be.visible)
        }
    }
}