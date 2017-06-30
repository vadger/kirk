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
}