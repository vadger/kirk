import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.Page
import com.automation.remarks.kirk.conditions.have
import org.testng.annotations.Test

/**
 * Created by sergey on 28.06.17.
 */
class VideoServiceTest : BaseTest() {

    @Test
    fun testCanLogin() {
        Browser.drive {
            to("http://localhost:8086")
            element("#inputEmail3").setVal("admin")
            element("#inputPassword3").setVal("admin")
            element("#parent > form > div:nth-child(3) > div > button").click()
            element("a.navbar-brand").should(have.text("Video service"))
        }
    }

    @Test
    fun testCanLoginPageObject() {
        Browser.drive {
            to(::LoginPage)
                    .login("admin", "admin")

        }
    }

    @Test
    fun testCanLoginPageObject2() {
        Browser.drive {
            to(::LoginPage)
                    .login("admin", "admin")

        }
    }
}

class LoginPage : Page() {

    override val url: String?
        get() = "http://localhost:8086"

    val loginInput = element("#inputEmail3")
    val passwordInput = element("#inputPassword3")

    fun login(name: String, password: String) {
        loginInput.setVal(name)
        passwordInput.setVal(password)
    }
}