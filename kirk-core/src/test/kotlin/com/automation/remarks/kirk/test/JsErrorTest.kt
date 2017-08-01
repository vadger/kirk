package com.automation.remarks.kirk.test

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.Kirk
import com.automation.remarks.kirk.conditions.size
import com.automation.remarks.kirk.conditions.text
import com.automation.remarks.kirk.ext.extractJSLogs
import org.openqa.selenium.firefox.FirefoxDriver
import org.testng.annotations.Test

/**
 * Created by alpa on 01.08.17.
 */
class JsErrorTest : BaseTest() {

        @Test
    fun testCanPrintJsErrorInChrome() {
        Kirk.drive {
            to(url)
            all(".list li").shouldHave(size(3))
        }
        extractJSLogs()
    }

    @Test
    fun testCanPrintJsErrorInFirefox() {
        val firefox = Browser(FirefoxDriver()).with { startMaximized = false }
        firefox.to(url)
        firefox.all(".list li").shouldHave(size(3))

        extractJSLogs()

        firefox.quit()
    }
}


