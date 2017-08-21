package com.automation.remarks.kirk.locators

import org.openqa.selenium.WebElement

/**
 * Created by sergey on 28.06.17.
 */
class CachedWebElementLocator(val locator: ElementLocator<List<WebElement>>,
                              val index: Int) : ElementLocator<WebElement> {
    override val description: String
        get() = "($locator)[$index]"

    override fun find(): WebElement {
        return locator.find()[index]
    }
}