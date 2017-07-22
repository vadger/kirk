package com.automation.remarks.kirk.test.vanila

import com.automation.remarks.kirk.Kirk
import com.automation.remarks.kirk.test.BaseTest
import com.automation.remarks.kirk.test.pages.StartPage
import org.openqa.selenium.NoAlertPresentException
import org.testng.annotations.Test

/**
 * Created by alpa on 7/11/17.
 */
class BrowserAlertTest : BaseTest() {

    @Test
    fun testCanAcceptAlert() {
        assertExceptionThrown(NoAlertPresentException::class) {
            Kirk.drive {
                baseUrl = url
                to(::StartPage) {
                    confirmBtn.click()
                    alert.accept()
                    driver.switchTo().alert()
                }
            }
        }
    }

    @Test fun testCanDismissAlertAlert() {
        assertExceptionThrown(NoAlertPresentException::class) {
            Kirk.drive {
                baseUrl = url
                to(::StartPage) {
                    confirmBtn.click()
                    alert.dismiss()
                    driver.switchTo().alert()
                }
            }
        }
    }

    @Test fun testCanGetAlertText() {
        Kirk.drive {
            baseUrl = url
            to(::StartPage) {
                confirmBtn.click()
                val alertText = alert.text
                alert.dismiss()
                assert(alertText == "Добрый день")
            }
        }
    }
}