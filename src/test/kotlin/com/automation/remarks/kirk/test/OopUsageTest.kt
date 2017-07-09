package com.automation.remarks.kirk.test

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.conditions.have
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.testng.annotations.Test

/**
 * Created by sergey on 28.06.17.
 */
class OopUsageTest : BaseTest() {

    @Test
    fun testСanRunBrowser() {
        val browser = Browser(ChromeDriver())
        browser.to(url)
        browser.element("#header").should(have.text("Kirk"))
        browser.quit()
    }

    @Test
    fun testCanSetNewDriver() {
        val firefox = Browser(FirefoxDriver())
        firefox.to(url)
        firefox.element(".paginator a").click()
        firefox.element("#header").should(have.text("Second page"))
        firefox.quit()
    }

    @Test
    fun testCanOpenTwoBrowser() {


        val ff = Browser(FirefoxDriver())
        ff.to(url)
        ff.all("li").should(have.size(3))
    }
}