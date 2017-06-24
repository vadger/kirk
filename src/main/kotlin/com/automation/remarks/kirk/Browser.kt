package com.automation.remarks.kirk

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

/**
 * Created by sergey on 24.06.17.
 */
interface Browser : WebDriver {

    companion object {
        fun drive(driver: WebDriver = ChromeDriver(), closure: Browser.() -> Unit): WebDriver {
            BrowserImpl(driver).apply {
                closure()
            }
            return driver
        }
    }

    fun to(url: String): String {
        get(url)
        return currentUrl
    }
}