package com.automation.remarks.kirk.core

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by sergey on 25.06.17.
 */
class ThreadLocalDriverContainer {

    private val driverContainer: MutableMap<Long, WebDriver> = ConcurrentHashMap(4)

    private fun createDriver(): WebDriver {
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