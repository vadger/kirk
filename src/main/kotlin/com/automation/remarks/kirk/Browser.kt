package com.automation.remarks.kirk

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

/**
 * Created by sergey on 24.06.17.
 */
class Browser(val driver: WebDriver) {

    companion object {

        private val driverContaner = ThreadLocalDriverContainer()

        fun getDriver(): WebDriver {
            return driverContaner.getDriver()
        }

        fun drive(driver: WebDriver = getDriver(), closure: Browser.() -> Unit) {
            Browser(driver).apply {
                Runtime.getRuntime().addShutdownHook(object : Thread() {
                    override fun run() = quit()
                })
                closure()
            }
        }
    }

    fun to(url: String): String {
        driver.get(url)
        return driver.currentUrl
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
        return KElement(driver.findElement(locator))
    }

    fun quit() {
        driver.quit()
    }
}