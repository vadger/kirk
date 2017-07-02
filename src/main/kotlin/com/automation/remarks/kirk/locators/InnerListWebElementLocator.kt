package com.automation.remarks.kirk.locators

import com.automation.remarks.kirk.KElement
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

/**
 * Created by sergey on 02.07.17.
 */
class InnerListWebElementLocator(private val by: By, private val element: KElement) : ElementLocator<List<WebElement>> {
    override fun find(): List<WebElement> {
        return element.webElement.findElements(by)
    }

    override val description: String
        get() = "($element).findAll($by)"
}