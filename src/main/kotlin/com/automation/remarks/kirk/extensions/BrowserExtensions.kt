package com.automation.remarks.kirk.extensions

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.KElement
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

/**
 * Created by sepi on 6/29/2017.
 */
fun Browser.element(locator: String, closure: WebElement.() -> Unit): KElement {
    return element(locator).execute(closure)
}

fun Browser.element(locator: By, closure: WebElement.() -> Unit) {
    element(locator).execute(closure)
}