package com.automation.remarks.kirk

import com.automation.remarks.kirk.conditions.Condition
import com.automation.remarks.kirk.conditions.be
import com.automation.remarks.kirk.locators.ElementLocator
import com.automation.remarks.kirk.locators.WebElementLocator
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement


/**
 * Created by sergey on 24.06.17.
 */
class KElement(val locator: ElementLocator<WebElement>) {

    constructor(locator: By, driver: WebDriver) : this(WebElementLocator(locator, driver))

    val webElement: WebElement
        get() = locator.find()

    fun click(): KElement {
        element { click() }
        return this
    }

    fun should(condition: Condition) {
        waitFor(this, condition)
    }

    fun setVal(value: String): KElement {
        element {
            clear()
            sendKeys(value)
        }
        return this
    }

    private fun element(commands: WebElement.() -> Unit) {
        try {
            webElement.apply(commands)
        } catch (ex: Exception) {
            waitFor(this, be.visible)
        }
    }

    override fun toString(): String {
        return locator.description
    }
}