package com.automation.remarks.kirk.locators

import com.automation.remarks.kirk.KElement
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

/**
 * Created by sergey on 02.07.17.
 */
class InnerWebElementLocator(private val by: By, private val element: KElement) : ElementLocator<WebElement> {
    override fun find(): WebElement {
        return element.webElement.findElement(by)
    }

    override val description: String
        get() = "($element).find($by)"
}