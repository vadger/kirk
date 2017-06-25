package com.automation.remarks.kirk

import org.openqa.selenium.TimeoutException
import org.openqa.selenium.WebElement

/**
 * Created by sergey on 25.06.17.
 */
fun waitFor(element: WebElement,
            condition: Condition,
            timeout: Int = 4000,
            poolingInterval: Long = 0.1.toLong()) {
    val endTime = System.currentTimeMillis() + timeout
    while (true) {
        try {
            return condition.evaluate(element)
        } catch (ex: ConditionMismatchException) {
            if (System.currentTimeMillis() > endTime) {
                val message = """
            failed while waiting ${timeout / 1000} seconds
            to assert $condition
            for $element
            reason: ${ex.message}
                        """
                throw TimeoutException(message)
            }
            Thread.sleep(poolingInterval)
        }
    }
}