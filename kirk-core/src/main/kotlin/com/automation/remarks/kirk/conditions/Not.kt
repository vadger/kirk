package com.automation.remarks.kirk.conditions

import org.openqa.selenium.WebElement

/**
 * Created by sepi on 24.07.17.
 */
class Not(val condition: Condition<WebElement>) : ElementCondition() {
    override fun matches(item: WebElement): Boolean {
        return !condition.matches(item)
    }

    override fun description(item: WebElement): String {
        return condition.description(item)
    }
}

fun not(condition: Condition<WebElement>): Not {
    return Not(condition)
}