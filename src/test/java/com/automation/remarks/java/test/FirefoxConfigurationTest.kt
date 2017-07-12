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
class FirefoxConfigurationTest : BaseTest() {

    @Test fun fireFoxTest() {
        System.setProperty("kirk.browser", "firefox")
        System.setProperty("kirk.startMaximized", "false")
        Browser.drive {
            to(url)
            assert(driver is FirefoxDriver)

            quit()
        }
    }
}