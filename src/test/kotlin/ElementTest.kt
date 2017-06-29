import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.conditions.have
import me.tatarka.assertk.assertions.isEqualTo
import org.testng.annotations.Test

/**
 * Created by sepi on 6/29/2017.
 */
class ElementTest : BaseTest() {

    @Test
    fun testElementCommands() {
        Browser.drive {
            to(url)
            element("#input") {
                clear()
                sendKeys("this is from test")
            }.should(have.attr("value", "demo"))
        }
    }

}