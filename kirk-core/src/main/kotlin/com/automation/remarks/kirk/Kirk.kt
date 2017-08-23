package com.automation.remarks.kirk

import com.automation.remarks.kirk.core.configuration
import com.automation.remarks.kirk.core.getDriver
import org.openqa.selenium.WebDriver

/**
 * Created by sergey on 22.07.17.
 */
class Kirk {

    companion object {

        private val browser = Browser()

        @JvmStatic
        fun open(url: String) {
            browser.to(url)
        }

        @JvmStatic
        fun <T : Page> open(pageClass: (Browser) -> T): T {
            return browser.to(pageClass)
        }

        @JvmStatic
        fun <T : Page> open(pageClass: (Browser) -> T, block: T.() -> Unit) {
            open(pageClass).block()
        }

        @JvmStatic
        fun <T : Page> at(pageClass: (Browser) -> T): T {
            return pageClass(browser)
        }

        @JvmStatic
        fun <T : Page> at(pageClass: (Browser) -> T, block: T.() -> Unit) {
            at(pageClass).block()
        }

        fun drive(driver: WebDriver = getDriver(), block: Browser.() -> Unit): Browser {
            val browser = Browser().with { config = configuration }
            browser.driver = driver
            browser.block()
            return browser
        }
    }
}