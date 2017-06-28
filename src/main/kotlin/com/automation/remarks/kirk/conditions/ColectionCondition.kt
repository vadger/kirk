package com.automation.remarks.kirk.conditions

import com.automation.remarks.kirk.ex.ConditionMismatchException
import org.openqa.selenium.WebElement

/**
 * Created by sergey on 28.06.17.
 */
abstract class CollectionCondition : Condition<List<WebElement>>()

class CollectionSize(val size: Int) : CollectionCondition() {
    override fun toString(): String {
        return "size"
    }

    override fun match(element: List<WebElement>): Boolean {
        val actual = element.size
        if (actual == size) {
            return true
        }
        throw ConditionMismatchException(actual.toString(), size.toString())
    }
}

class CollectionExactText(val text: Array<out String>) : CollectionCondition() {
    override fun toString(): String {
        return "exact text"
    }

    override fun match(element: List<WebElement>): Boolean {
        val actual = element.map { it.text }
        val expected = text.toList()
        if (actual.equals(expected)) {
            return true
        }
        throw ConditionMismatchException(actual.toString(), expected.toString())
    }

}