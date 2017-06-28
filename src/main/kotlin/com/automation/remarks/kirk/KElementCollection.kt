package com.automation.remarks.kirk

import com.automation.remarks.kirk.conditions.CollectionCondition
import com.automation.remarks.kirk.locators.ElementLocator
import com.automation.remarks.kirk.locators.WebElementListLocator
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

/**
 * Created by sergey on 28.06.17.
 */
class KElementCollection(private val locator: ElementLocator<List<WebElement>>) : Collection<List<WebElement>> {

    constructor(locator: By, driver: WebDriver) : this(WebElementListLocator(locator, driver))

    val webElements: List<WebElement>
        get() = locator.find()

    override val size: Int
        get() = webElements.size //To change initializer of created properties use File | Settings | File Templates.

    fun should(condition: CollectionCondition) {
        waitFor(this, condition)
    }

    override fun contains(element: List<WebElement>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun containsAll(elements: Collection<List<WebElement>>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isEmpty(): Boolean {
        return webElements.isEmpty()
    }

    override fun iterator(): Iterator<List<WebElement>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toString(): String {
        return locator.description
    }
}