package com.automation.remarks.kirk.test

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.conditions.be
import com.automation.remarks.kirk.conditions.have
import org.openqa.selenium.TimeoutException
import org.testng.annotations.Test

/**
 * Created by sepi on 6/30/2017.
 */
class ConditionsTest : BaseTest() {
    @Test
    fun testShouldThrowTimeOutException() {
        assertExceptionThrown(TimeoutException::class) {
            Browser.drive {
                to(url)
                element("#header").should(have.text("KIr"))
            }
        }
    }

    @Test
    fun testShouldThrowExceptionAtInvisibleElement() {
        assertExceptionThrown(TimeoutException::class) {
            Browser.drive {
                to(url)
                element("#input_invisible").setVal("demo")
            }
        }
    }

    @Test
    fun testShouldFailOnWrongSelector() {
        assertExceptionThrown(TimeoutException::class) {
            Browser.drive {
                to(url)
                element("#wrongLocator").should(be.visible)
            }
        }
    }

    @Test
    fun testShouldFailOnWrongCollectionLocator() {
        assertExceptionThrown(TimeoutException::class) {
            Browser.drive {
                to(url)
                all("@wrongLocator").should(have.size(5))
            }
        }
    }

    @Test
    fun shouldCompareCollectionText() {
        Browser.drive {
            to(url)
            all("li").should(have.exactText("Один", "Два", "Три"))
        }
    }

    @Test
    fun testShouldFindCollectionOfElements() {
        Browser.drive {
            to(url)
            all("li").should(have.size(3))
        }
    }

    @Test
    fun testShouldThrowExceptionOnWrongNumberOfElements() {
        assertExceptionThrown(TimeoutException::class) {
            Browser.drive {
                to(url)
                all("li").should(have.size(2))
            }
        }
    }

    @Test
    fun testShouldHighlightButton() {
        assertExceptionThrown(TimeoutException::class) {
            Browser.drive {
                to(url)
                element("#button").should(have.attr("value", "Глупый клик"))
            }
        }
    }
}