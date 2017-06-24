import com.automation.remarks.kirk.Browser.Companion.drive
import io.github.bonigarcia.wdm.ChromeDriverManager
import org.testng.Assert.assertEquals
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

/**
 * Created by sergey on 24.06.17.
 */
class TestBrowser {

    @BeforeClass
    fun setUp() {
        ChromeDriverManager.getInstance().setup()
    }

    @Test
    fun testCanOpenUrl() {
        val url = "file:///home/sergey/Github/kirk/src/test/resources/test.html"

        drive {
            to(url)
            assertEquals(currentUrl, url)
        }
    }
}