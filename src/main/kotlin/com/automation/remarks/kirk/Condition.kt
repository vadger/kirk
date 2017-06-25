package com.automation.remarks.kirk

import org.openqa.selenium.WebElement

/**
 * Created by sergey on 24.06.17.
 */
abstract class Condition {
    fun evaluate(element: WebElement) {
        match(element)
    }

    abstract fun match(element: WebElement)
}

class Text(val text: String) : Condition() {
    override fun match(element: WebElement) {
        val actual = element.text
        if (!actual.equals(text)) {
            throw ConditionMismatchException(actual, text)
        }
    }

    override fun toString(): String {
        return "text"
    }
}

class Visible : Condition() {
    override fun match(element: WebElement) {
        if (!element.isDisplayed) {
            throw ConditionMismatchException("invisible", "visible")
        }
    }

    override fun toString(): String {
        return "element visibility"
    }
}

