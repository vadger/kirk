package com.automation.remarks.kirk.conditions

import org.openqa.selenium.WebElement

/**
 * Created by sergey on 28.06.17.
 */
abstract class CollectionCondition : Condition<List<WebElement>>()

class CollectionSize(val size: Int) : CollectionCondition() {
    override fun matches(item: List<WebElement>): Boolean {
        return item.size == size
    }

    override fun description(item: List<WebElement>): Description {
        return Description(item.size, size)
    }
}

class CollectionExactText(val text: Array<out String>) : CollectionCondition() {
    override fun matches(item: List<WebElement>): Boolean {
        return item.map { it.text } == text.toList()
    }

    override fun description(item: List<WebElement>): Description {
        return Description(item.map { it.text }, text.toList())
    }
}

class CollectionContainText(val text: String) : CollectionCondition() {
    override fun matches(item: List<WebElement>): Boolean {
        return item.any { it.text == text }
    }

    override fun description(item: List<WebElement>): Description {
        return Description(item.map { it.text }, text)
    }

    override fun toString(): String {
        return "collection have element with text"
    }
}