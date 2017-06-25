package com.automation.remarks.kirk

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

/**
 * Created by sergey on 24.06.17.
 */
class BrowserImpl(private val driver: WebDriver) {

    fun to(url: String): String {
        driver.get(url)
        return driver.currentUrl
    }

    fun <T> to(url: String, pageClass: () -> T): T {
        val page = pageClass()
        to(url)
        return page
    }

    fun <T> to(pageClass: () -> T): T {
        return pageClass()
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

    val currentUrl: String
        get() = driver.currentUrl
}