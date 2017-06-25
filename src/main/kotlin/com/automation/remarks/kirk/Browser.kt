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
        private var screenSize: String? = null

        private fun getDefaultDriver(): WebDriver {
            return driver
        }

        fun drive(screenSize: String? = null, driver: WebDriver = getDefaultDriver(),
                  closure: Browser.() -> Unit) {
            BrowserImpl(driver).apply {
                Runtime.getRuntime().addShutdownHook(object : Thread() {
                    override fun run() = quit()
                })

                setWindowSize(screenSize)
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

    fun element(cssLocator: String): KElement {
        return KElement(findElement(By.cssSelector(cssLocator)))
    }
}