package com.automation.remarks.kirk.conditions

import com.automation.remarks.kirk.ex.ConditionMismatchException
import org.openqa.selenium.WebElement

/**
 * Created by sergey on 28.06.17.
 */
abstract class ElementCondition : Condition<WebElement>()

class Text(val text: String) : ElementCondition() {
    override fun match(element: WebElement): Boolean {
        val actual = element.text
        if (actual.equals(text)) {
            return true
        }
        throw ConditionMismatchException(actual, text)
    }

    override fun toString(): String {
        return "text"
    }
}

class Visible : ElementCondition() {
    override fun match(element: WebElement): Boolean {
        if (element.isDisplayed) {
            return true
        }
        throw ConditionMismatchException("invisible", "visible")

    }

    override fun toString(): String {
        return "element visibility"
    }
}
