package com.automation.remarks.kirk.conditions

import com.automation.remarks.kirk.ext.classes
import org.openqa.selenium.WebElement

/**
 * Created by sergey on 28.06.17.
 */
abstract class ElementCondition : BaseCondition<WebElement>()

class Text(val text: String) : ElementCondition() {

    override fun matches(item: WebElement): Boolean {
        return item.text == text
    }

    override fun description(item: WebElement): Description {
        return Description(item.text, text)
    }
}

class Visibility : ElementCondition() {
    override fun matches(item: WebElement): Boolean {
        return item.isDisplayed
    }

    override fun description(item: WebElement): Description {
        return Description("invisible", "visible", diff = false)
    }
}

class AttributeValue(val attr: String, val expect: String) : ElementCondition() {
    override fun matches(item: WebElement): Boolean {
        return item.getAttribute(attr) == expect
    }

    override fun description(item: WebElement): Description {
        return Description(item.getAttribute(attr), expect)
    }

    override fun toString(): String {
        return "attribute value {$attr}"
    }
}

class CssClassValue(val cssClass: String) : ElementCondition() {
    override fun matches(item: WebElement): Boolean {
        return item.classes.contains(cssClass)
    }

    override fun description(item: WebElement): Description {
        return Description(item.classes, cssClass, diff = false)
    }
}