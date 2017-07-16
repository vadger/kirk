package com.automation.remarks.kirk.locators

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

/**
 * Created by sepi on 6/27/2017.
 */
class WebElementLocator(private val by: By,
                        private val driver: WebDriver) : ElementLocator<WebElement> {
    override fun find(): WebElement {
        return driver.findElement(by)
    }

    override val description: String
        get() = by.toString()
}