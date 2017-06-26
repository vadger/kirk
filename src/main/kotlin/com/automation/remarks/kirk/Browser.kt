package com.automation.remarks.kirk

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

/**
 * Created by sergey on 24.06.17.
 */
interface Browser : WebDriver {
    companion object {

        private val driverContaner = ThreadLocalDriverContainer()

        fun getDriver(): WebDriver {
            return driverContaner.getDriver()
        }

        fun drive(closure: Browser.() -> Unit) {
            BrowserHandler(getDriver()).apply {
                Runtime.getRuntime().addShutdownHook(object : Thread() {
                    override fun run() = quit()
                })
                closure()
            }
        }
    }

    fun to(url: String): String {
        get(url)
        return currentUrl
    }

    fun <T : Page> to(pageClass: () -> T): T {
        val page = pageClass()
        page.browser = this
        page.url?.let { to(it) }
        return page
    }

    fun element(cssLocator: String): KElement {
        return element(By.cssSelector(cssLocator))
    }

    fun element(locator: By): KElement {
        return KElement(findElement(locator))
    }
}