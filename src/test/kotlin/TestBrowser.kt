import com.automation.remarks.kirk.Browser.Companion.drive
import com.automation.remarks.kirk.conditions.be
import com.automation.remarks.kirk.conditions.have
import com.codeborne.selenide.Condition
import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import me.tatarka.assertk.assert
import me.tatarka.assertk.assertions.isEqualTo
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
            val currentUrl = to(url).currentUrl
            assertEquals(currentUrl, url)
        }
    }

    @Test
    fun testCanFindElement() {
        drive {
            to(url)
            element("#header").should(have.text("Kirk"))
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
                element("#header").should(have.text("KIr"))
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
        assertExceptionThrown(TimeoutException::class) {
            drive {
                to(url)
                element("#wrongLocator").should(be.visible)
            }
        }
    }

    @Test
    fun testShouldFindCollectionOfElements() {
        drive {
            to(url)
            all("li").should(have.collectionSize(3))
        }
    }

    @Test
    fun testShouldThrowExceptionOnWrongNumberOfElements() {
        assertExceptionThrown(TimeoutException::class) {
            drive {
                to(url)
                all("li").should(have.collectionSize(2))
            }
        }
    }

    @Test
    fun testShouldFailOnWrongCollectionLocator() {
        assertExceptionThrown(TimeoutException::class) {
            drive {
                to(url)
                all("@wrongLocator").should(have.collectionSize(5))
            }
        }
    }

    @Test
    fun shouldIterateOverCollection() {
        drive {
            to(url)
            for (el in all("li")) {
                print(el.text)
            }
        }
    }

    @Test
    fun shouldCompareCollectionText() {
        drive {
            to(url)
            all("li").should(have.exactText("Один", "Два", "Три"))
        }
    }

    @Test
    fun testSite() {
        drive {
            to("http://arvi-qa-am.pp.ciklum.com")
            element(".header-account-link  li:nth-child(1) a").click()
            element("#email").setVal("wrong@mail.com")
            element("#pass").setVal("wrongpass")
            element("#send2").click()
            element(".error-msg span").should(have.text("Invali login or password."))
        }
    }

    @Test
    fun testSiteSelenide() {
        drive {
            Configuration.browser = "chrome"
            Selenide.open("http://arvi-qa-am.pp.ciklum.com")
            Selenide.`$`(".header-account-link  li:nth-child(1) a").click()
            Selenide.`$`("#email").value = "wrong@mail.com"
            Selenide.`$`("#pass").value = "wrongpass"
            Selenide.`$`("#send2").click()
            Selenide.`$`(".error-msg span").shouldHave(Condition.text("Invalid login or password."))
        }
    }

}