package com.automation.remarks.kirk.locators

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

/**
 * Created by sergey on 28.06.17.
 */
class WebElementListLocator(private val by: By, private val driver: WebDriver) : ElementLocator<List<WebElement>> {
    override fun find(): List<WebElement> {
        return driver.findElements(by)
    }

    override val description: String
        get() = this.by.toString()
}