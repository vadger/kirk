package com.automation.remarks.kirk

import org.openqa.selenium.WebElement

/**
 * Created by sergey on 24.06.17.
 */
abstract class Condition {
    fun evaluate(element: WebElement): Boolean {
        val match = match(element)
        assert(match)
        return match
    }

    abstract fun match(element: WebElement): Boolean
}

class Text(val text: String) : Condition() {
    override fun match(element: WebElement): Boolean {
        return element.text.equals(text)
    }
}