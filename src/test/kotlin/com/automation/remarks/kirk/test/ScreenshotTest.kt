package com.automation.remarks.kirk.test

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.conditions.have
import com.automation.remarks.kirk.core.ScreenshotContainer
import me.tatarka.assertk.assert
import me.tatarka.assertk.assertions.isEqualTo
import me.tatarka.assertk.assertions.isNotNull
import org.openqa.selenium.TimeoutException
import org.testng.annotations.Test

/**
 * Created by sergey on 03.07.17.
 */
class ScreenshotTest : BaseTest() {

    @Test fun testCanGetPathToTheLatestScreenshotOnConditionFail() {
        Browser.drive {
            to(url)
            assertExceptionThrown(TimeoutException::class) {
                Browser.drive {
                    to(url)
                    all("li").should(have.size(1))
                }
            }
        }

        assert(ScreenshotContainer.getLatestScreenshot()).isNotNull()
    }

    @Test fun testCanGetPathToTheLatestScreenshotOnWrongLocator() {
        Browser.drive {
            to(url)
            assertExceptionThrown(TimeoutException::class) {
                Browser.drive {
                    to(url)
                    all("@").should(have.size(1))
                }
            }
        }

        assert(ScreenshotContainer.getLatestScreenshot()).isNotNull()
    }

    @Test fun testCanTakeAScreenshotImplicitly() {
        val userDir = System.getProperty("user.dir")

        Browser.drive {
            to(url)
            takeScreenshot("$userDir/build/browser.png")
        }

        assert(ScreenshotContainer.getLatestScreenshot()?.absolutePath).isEqualTo("$userDir/build/browser.png")
    }
}