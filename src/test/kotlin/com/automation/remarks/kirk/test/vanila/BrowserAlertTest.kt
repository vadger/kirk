package com.automation.remarks.kirk.test.vanila

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.core.drive
import com.automation.remarks.kirk.test.BaseTest
import com.automation.remarks.kirk.test.pages.StartPage
import me.tatarka.assertk.fail
import org.openqa.selenium.NoAlertPresentException
import org.testng.annotations.Test

/**
 * Created by alpa on 7/11/17.
 */
class BrowserAlertTest : BaseTest() {

    @Test
    fun testCanAcceptAlert() {
        assertExceptionThrown(NoAlertPresentException::class) {
            Browser.drive {
                baseUrl = url
                to(::StartPage) {
                    confirmBtn.click()
                    acceptAlert()
                    driver.switchTo().alert()
                }
            }
        }
    }

    @Test fun testCanDismissAlertAlert() {
        assertExceptionThrown(NoAlertPresentException::class) {
            Browser.drive {
                baseUrl = url
                to(::StartPage) {
                    confirmBtn.click()
                    dismissAlert()
                    driver.switchTo().alert()
                }
            }
        }
    }

}