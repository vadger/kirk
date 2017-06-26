package com.automation.remarks.kirk

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

/**
 * Created by sergey on 26.06.17.
 */
class DriverlessBrowser : Browser {
    override fun getWindowHandles(): MutableSet<String> {
        throw UnsupportedOperationException()
    }

    override fun findElement(by: By?): WebElement {
        throw UnsupportedOperationException()
    }

    override fun getWindowHandle(): String {
        throw UnsupportedOperationException()
    }

    override fun getPageSource(): String {
        throw UnsupportedOperationException()
    }

    override fun navigate(): WebDriver.Navigation {
        throw UnsupportedOperationException()
    }

    override fun manage(): WebDriver.Options {
        throw UnsupportedOperationException()
    }

    override fun getCurrentUrl(): String {
        throw UnsupportedOperationException()
    }

    override fun getTitle(): String {
        throw UnsupportedOperationException()
    }

    override fun get(url: String?) {
        throw UnsupportedOperationException()
    }

    override fun switchTo(): WebDriver.TargetLocator {
        throw UnsupportedOperationException()
    }

    override fun close() {
        throw UnsupportedOperationException()
    }

    override fun quit() {
        throw UnsupportedOperationException()
    }

    override fun findElements(by: By?): MutableList<WebElement> {
        throw UnsupportedOperationException()
    }
}