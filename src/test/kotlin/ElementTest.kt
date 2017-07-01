
import com.automation.remarks.kirk.Browser.Companion.drive
import com.automation.remarks.kirk.conditions.have
import com.automation.remarks.kirk.extensions.element
import me.tatarka.assertk.assert
import me.tatarka.assertk.assertions.isEqualTo
import org.testng.annotations.Test

/**
 * Created by sepi on 6/29/2017.
 */
class ElementTest : BaseTest() {

    @Test fun testElementCommands() {
        drive {
            to(url)
            element("#input") {
                clear()
                sendKeys("this is from test")
            }.should(have.attr(name = "value", value = "this is from test"))
        }
    }

    @Test fun testCanClickOnElement() {
        drive {
            to(url)
            element("#button").click()
        }
    }

    @Test fun testCanCheckElementDescription(){
        drive {
            to(url)
            val description = element("#button").toString()

            assert(description).isEqualTo("By.cssSelector: #button")
        }
    }
}