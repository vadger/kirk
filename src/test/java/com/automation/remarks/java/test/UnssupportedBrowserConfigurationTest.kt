package com.automation.remarks.java.test

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.core.drive
import com.automation.remarks.kirk.test.BaseTest
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.testng.annotations.Test

/**
 * Created by alpa on 7/12/17.
 */
class UnssupportedBrowserConfigurationTest : BaseTest() {

    @Test fun unsupportedBrowserTest() {
        System.setProperty("kirk.browser", "testbrowser")
        assertExceptionThrown(IllegalArgumentException::class) {
            Browser.drive {
                to(url)
                assert(driver is FirefoxDriver)

                quit()
            }
        }
    }
}