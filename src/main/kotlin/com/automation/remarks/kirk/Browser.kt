package com.automation.remarks.kirk

import com.automation.remarks.kirk.Browser.Companion.getDriver
import org.aeonbits.owner.ConfigFactory
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

/**
 * Created by sergey on 24.06.17.
 */
open class Browser(val driver: WebDriver = getDriver()) {

    var config: BrowserConfig = getConfig()

    companion object {

        private val driverContainer = ThreadLocalDriverContainer()

        fun getDriver(): WebDriver {
            return driverContainer.getDriver()
        }

        fun setDriver(driver: WebDriver) {
            driverContainer.setWebDriver(driver)
        }

        fun getConfig(): BrowserConfig {
            return ConfigFactory.create(BrowserConfig::class.java,
                    System.getProperties())
        }

        fun drive(closure: Browser.() -> Unit) {
            Browser(getDriver()).apply(closure)
        }
    }

    fun to(url: String): Browser {
        addHooks()
        if (isAbsoluteUrl(url)) {
            driver.get(url)
        } else {
            driver.get(config.baseUrl() + url)
        }
        return this
    }

    private fun addHooks() {
        Runtime.getRuntime().addShutdownHook(object : Thread() {
            override fun run() = quit()
        })
    }

    private fun isAbsoluteUrl(url: String): Boolean {
        return (url.startsWith("http://") || url.startsWith("https://"))
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
        return KElement(locator, driver)
    }

    fun all(cssLocator: String): KElementCollection {
        return all(By.cssSelector(cssLocator))
    }

    fun all(locator: By): KElementCollection {
        return KElementCollection(locator, driver)
    }

    val currentUrl: String by lazy {
        driver.currentUrl
    }

    fun quit() {
        driver.quit()
    }
}