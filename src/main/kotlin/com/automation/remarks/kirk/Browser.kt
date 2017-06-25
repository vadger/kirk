package com.automation.remarks.kirk

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

/**
 * Created by sergey on 24.06.17.
 */
interface Browser : WebDriver {

    companion object {
        private var driver: WebDriver = ChromeDriver()
        private var config: BrowserConfig = BrowserConfig()

        private fun getDefaultDriver(): WebDriver {
            return driver
        }

        fun conf(config: BrowserConfig) {
            this.config = config
        }

        fun drive(driver: WebDriver = getDefaultDriver(),
                  closure: Browser.() -> Unit) {
            BrowserImpl(driver).apply {
                addShutdownHook()
                setWindowSize(config.screenSize)
                closure()
            }
        }

        fun open(url: String, closure: Browser.() -> Unit) {
            BrowserImpl(driver).apply {
                get(url)
                addShutdownHook()
                setWindowSize(config.screenSize)
                closure()
            }
        }
    }

    fun to(url: String): String {
        get(url)
        return currentUrl
    }

    fun <T> to(url: String, pageClass: () -> T): T {
        val page = pageClass()
        to(url)
        return page
    }

    fun s(cssLocator: String): KElement {
        return KElement(findElement(By.cssSelector(cssLocator)))
    }

    fun sleep(millis: Long) {
        Thread.sleep(millis)
    }
}