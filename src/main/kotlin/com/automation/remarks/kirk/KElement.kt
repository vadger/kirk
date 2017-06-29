package com.automation.remarks.kirk

import com.automation.remarks.kirk.conditions.ElementCondition
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
        execute { click() }
        return this
    }

    fun should(condition: ElementCondition) {
        waitFor(this.locator, condition)
    }

    fun setVal(value: String): KElement {
        execute {
            clear()
            sendKeys(value)
        }
        return this
    }

    fun execute(commands: WebElement.() -> Unit): KElement {
        try {
            webElement.apply(commands)
        } catch (ex: Exception) {
            waitFor(this.locator, be.visible)
        }
        return this
    }

    override fun toString(): String {
        return locator.description
    }

    val text: String by lazy {
        webElement.text
    }
}