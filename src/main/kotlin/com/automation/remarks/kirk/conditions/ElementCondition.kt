package com.automation.remarks.kirk.conditions

import com.automation.remarks.kirk.ex.ConditionMismatchException
import com.automation.remarks.kirk.fail
import org.openqa.selenium.WebElement

/**
 * Created by sergey on 28.06.17.
 */
abstract class ElementCondition : Condition<WebElement>()

class Text(val text: String) : ElementCondition() {
    override fun match(element: WebElement) {
        val actual = element.text
        if (actual.equals(text)) {
            return
        }
        fail(text, actual)
    }

    override fun toString(): String {
        return "text"
    }
}

class Visible : ElementCondition() {
    override fun match(element: WebElement) {
        if (element.isDisplayed) {
            return
        }
        fail("visible", "invisible", withDiff = false)
    }

    override fun toString(): String {
        return "execute visibility"
    }
}

class AttributeValue(val attr: String, val expect: String) : ElementCondition() {
    override fun match(element: WebElement) {
        val actual = element.getAttribute(attr)
        if (actual.equals(expect)) {
            return
        }
        fail(expect, actual)
    }

    override fun toString(): String {
        return "attribute {$attr}"
    }
}
