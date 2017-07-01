package com.automation.remarks.kirk

import com.automation.remarks.kirk.conditions.ElementCondition
import com.automation.remarks.kirk.conditions.be
import com.automation.remarks.kirk.locators.ElementLocator
import com.automation.remarks.kirk.locators.WebElementLocator
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions


/**
 * Created by sergey on 24.06.17.
 */
class KElement(private val locator: ElementLocator<WebElement>, driver: WebDriver) {

    val actions: Actions = Actions(driver)

    constructor(locator: By, driver: WebDriver) :
            this(WebElementLocator(locator, driver), driver)

    val webElement: WebElement
        get() = locator.find()

    fun click() {
        execute { click() }
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

    fun pressEnter() {
        execute { sendKeys(Keys.ENTER) }
    }

    fun hover(): KElement {
        actions.moveToElement(webElement).build().perform()
        return this
    }
}