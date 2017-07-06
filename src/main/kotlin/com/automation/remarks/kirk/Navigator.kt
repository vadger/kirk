package com.automation.remarks.kirk

import org.openqa.selenium.WebDriver

/**
 * Created by sergey on 02.07.17.
 */
class Navigator(private val driver: WebDriver) {

    fun <T : Page> at(pageClass: () -> T, closure: T.() -> Unit) {
        return pageClass().closure()
    }

    private fun isAbsoluteUrl(url: String): Boolean {
        return (url.startsWith("http://") || url.startsWith("https://"))
    }

    fun back(): Navigator {
        driver.navigate().back()
        return this
    }

    fun forward(): Navigator {
        driver.navigate().forward()
        return this
    }

    fun refresh(): Navigator {
        driver.navigate().refresh()
        return this
    }

    fun to(url: String) {
        if (isAbsoluteUrl(url)) {
            driver.navigate().to(url)
        } else {
            driver.navigate().to(Browser.getConfig().baseUrl() + url)
        }
    }
}