package com.automation.remarks.kirk.conditions

import com.automation.remarks.kirk.ext.classes
import com.automation.remarks.kirk.core.fail
import org.openqa.selenium.WebElement

/**
 * Created by sergey on 28.06.17.
 */
abstract class ElementCondition : Condition<WebElement>()

class Text(val text: String) : ElementCondition() {
    override fun match(element: WebElement): WebElement {
        val actual = element.text
        if (actual == text) {
            return element
        }
        throw fail(text, actual)
    }
}

class ElementVisibility : ElementCondition() {
    override fun match(element: WebElement): WebElement {
        if (element.isDisplayed) {
            return element
        }
        throw fail("visible", "invisible", withDiff = false)
    }
}

class AttributeValue(val attr: String, val expect: String) : ElementCondition() {
    override fun match(element: WebElement): WebElement {
        val actual = element.getAttribute(attr)
        if (actual == expect) {
            return element
        }
        throw fail(expect, actual)
    }

    override fun toString(): String {
        return "attribute {$attr}"
    }
}

class CssClassValue(val cssClass: String) : ElementCondition() {
    override fun match(element: WebElement): WebElement {
        val cssValue = element.classes
        if (cssValue.contains(cssClass)) {
            return element
        }
        throw fail(cssClass, cssValue, withDiff = false)
    }

    override fun toString(): String {
        return "class value"
    }
}