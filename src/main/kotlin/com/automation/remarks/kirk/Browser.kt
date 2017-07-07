package com.automation.remarks.kirk

import com.automation.remarks.kirk.core.JsExecutor
import com.automation.remarks.kirk.core.WebDriverFactory
import org.aeonbits.owner.ConfigFactory
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

/**
 * Created by sergey on 07.07.17.
 */
interface Browser {

    companion object {

        private val driverFactory = WebDriverFactory()

        fun getDriver(): WebDriver {
            return driverFactory.getDriver()
        }

        fun setDriver(driver: WebDriver) {
            driverFactory.setWebDriver(driver)
        }

        fun getConfig(): Configuration {
            return ConfigFactory.create(Configuration::class.java,
                    System.getProperties())
        }

        fun drive(driver: WebDriver = getDriver(), closure: BrowserHandler.() -> Unit) {
            BrowserHandler(driver).apply(closure)
        }
    }

    fun to(url: String)

    fun <T : Page> to(pageClass: () -> T, block: T.() -> Unit): Navigator

    fun element(byCss: String): KElement {
        return element(By.cssSelector(byCss))
    }

    fun element(by: By): KElement

    fun all(byCss: String): KElementCollection {
        return all(By.cssSelector(byCss))
    }

    fun all(by: By): KElementCollection

    val currentUrl: String

    val js: JsExecutor
}