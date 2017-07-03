package com.automation.remarks.kirk.test
import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.conditions.have
import org.openqa.selenium.firefox.FirefoxDriver
import org.testng.annotations.Test

/**
 * Created by sergey on 28.06.17.
 */
class OOPUsageTest : BaseTest() {

    @Test
    fun testСanRunBrowser() {
        Browser()
                .to(url)
                .element("#header")
                .should(have.text("Kirk"))
    }

    @Test
    fun testCanSetNewDriver() {
        Browser(FirefoxDriver())
                .to(url)
    }
}