package com.automation.remarks.kirk

import org.openqa.selenium.WebElement

/**
 * Created by sergey on 24.06.17.
 */
class KElement(val element: WebElement) {

    private fun should(condition: Condition) {
        condition.evaluate(element)
    }

    fun shouldHave(condition: Condition) {
        should(condition)
    }

    fun shouldBe(condition: Condition) {
        should(condition)
    }
}