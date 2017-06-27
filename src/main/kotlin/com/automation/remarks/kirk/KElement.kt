package com.automation.remarks.kirk

import com.automation.remarks.kirk.conditions.Condition
import org.openqa.selenium.WebElement

/**
 * Created by sergey on 24.06.17.
 */
class KElement(val element: WebElement) : WebElement by element {

    fun should(condition: Condition) {
        waitFor(element, condition)
    }

    fun setVal(value: String): KElement {
        element.clear()
        element.sendKeys(value)
        return this
    }
}