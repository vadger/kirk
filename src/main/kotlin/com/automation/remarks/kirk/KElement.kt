package com.automation.remarks.kirk

import org.openqa.selenium.WebElement

/**
 * Created by sergey on 24.06.17.
 */
class KElement(val element: WebElement) : WebElement by element {

    fun should(condition: Condition) {
        waitFor(element, condition)
    }

    fun setVal(value: String) {
        element.clear()
        element.sendKeys(value)
    }
}