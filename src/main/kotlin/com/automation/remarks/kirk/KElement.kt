package com.automation.remarks.kirk

import org.openqa.selenium.WebElement

/**
 * Created by sergey on 24.06.17.
 */
class KElement(val element: WebElement) {

    fun should(condition: Text) {
        condition.evaluate(element.text)
    }
}