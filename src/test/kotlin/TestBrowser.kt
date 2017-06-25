import com.automation.remarks.kirk.Browser.Companion.drive
import com.automation.remarks.kirk.Text
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
    fun testCanFindElement() {
        drive {
            to(url)
            element("#header").should(Have.text("Selene"))
        }
    }

    @Test
    fun testCanSetScreenSize() {
        drive(screenSize = "640x480") {
            to(url)
        }

    }
}

class Have {
    companion object {
        fun text(text: String): Text {
            return Text(text)
        }
    }
}

class StartPage