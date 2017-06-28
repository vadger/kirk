package com.automation.remarks.kirk

import com.automation.remarks.kirk.conditions.CollectionCondition
import com.automation.remarks.kirk.locators.CachedWebElementLocator
import com.automation.remarks.kirk.locators.ElementLocator
import com.automation.remarks.kirk.locators.WebElementListLocator
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

/**
 * Created by sergey on 28.06.17.
 */
class KElementCollection(private val locator: ElementLocator<List<WebElement>>) : Collection<KElement> {

    constructor(locator: By, driver: WebDriver) : this(WebElementListLocator(locator, driver))

    val webElements: List<WebElement>
        get() = locator.find()

    override val size: Int
        get() = webElements.size //To change initializer of created properties use File | Settings | File Templates.

    fun should(condition: CollectionCondition) {
        waitFor(this, condition)
    }

    override fun isEmpty(): Boolean {
        return webElements.isEmpty()
    }

    override fun iterator(): Iterator<KElement> = object : Iterator<KElement> {
        var index = 0

        override fun hasNext(): Boolean {
            return webElements.size > this.index
        }

        override fun next(): KElement {
            val indexedElement = KElement(CachedWebElementLocator(webElements[index],
                    String.format("(%s)[%s]", webElements, index)))
            this.index += 1
            return indexedElement
        }
    }

    override fun toString(): String {
        return locator.description
    }

    override fun contains(element: KElement): Boolean {
        throw NotImplementedError()
    }

    override fun containsAll(elements: Collection<KElement>): Boolean {
        throw NotImplementedError()
    }
}