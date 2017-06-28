package com.automation.remarks.kirk.locators

import org.openqa.selenium.WebElement

/**
 * Created by sergey on 28.06.17.
 */
class CachedWebElementLocator(val webElement: WebElement, val label: String) : ElementLocator<WebElement> {
    override val description: String
        get() = label

    override fun find(): WebElement {
        return webElement
    }
}