package com.automation.remarks.kirk.conditions

import com.automation.remarks.kirk.core.fail
import org.openqa.selenium.WebElement

/**
 * Created by sergey on 28.06.17.
 */
abstract class CollectionCondition : Condition<List<WebElement>>()

class CollectionSize(val size: Int) : CollectionCondition() {
    override fun toString(): String {
        return "size"
    }

    override fun match(element: List<WebElement>): List<WebElement> {
        val actual = element.size
        if (actual == size) {
            return element
        }
        throw fail(size, actual)
    }
}

class CollectionExactText(val text: Array<out String>) : CollectionCondition() {
    override fun toString(): String {
        return "exact text"
    }

    override fun match(element: List<WebElement>): List<WebElement> {
        val actual = element.map { it.text }
        val expected = text.toList()
        if (actual.equals(expected)) {
            return element
        }
        throw fail(expected, actual)
    }
}

class CollectionContainText(val text: String) : CollectionCondition() {
    override fun match(elements: List<WebElement>): List<WebElement> {
        if (elements.any { it.text == text }) {
            return elements
        }
        throw fail(text, elements.map { it.text })
    }

    override fun toString(): String {
        return "collection contains element with text"
    }
}