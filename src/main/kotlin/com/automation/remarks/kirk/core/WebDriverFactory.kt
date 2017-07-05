package com.automation.remarks.kirk.core

import io.github.bonigarcia.wdm.ChromeDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by sergey on 25.06.17.
 */
class WebDriverFactory {

    private val driverContainer: MutableMap<Long, WebDriver> = ConcurrentHashMap(4)

    private fun createDriver(): WebDriver {
        return createChromeDriver()
    }

    private fun createChromeDriver(): WebDriver {
        ChromeDriverManager.getInstance().setup()
        return ChromeDriver()
    }

    fun setWebDriver(webDriver: WebDriver): WebDriver {
        driverContainer.put(Thread.currentThread().id, webDriver)
        return webDriver
    }

    fun getDriver(): WebDriver {
        val driver = driverContainer[Thread.currentThread().id]
        if (driver != null) {
            return driver
        }
        return setWebDriver(createDriver())
    }
}