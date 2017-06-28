package com.automation.remarks.kirk

import com.automation.remarks.kirk.conditions.CollectionCondition
import com.automation.remarks.kirk.conditions.Condition
import com.automation.remarks.kirk.ex.ConditionMismatchException
import org.openqa.selenium.TimeoutException

/**
 * Created by sergey on 25.06.17.
 */
fun waitFor(elements: KElementCollection, condition: CollectionCondition, timeout: Int = 4000, poolingInterval: Long = 0.1.toLong()) {
    val endTime = System.currentTimeMillis() + timeout
    while (true) {
        try {
            return condition.evaluate(elements.webElements)
        } catch (ex: ConditionMismatchException) {
            if (System.currentTimeMillis() > endTime) {
                val message = """
            failed while waiting ${timeout / 1000} seconds
            to assert $condition
            for all elements located {$elements}
            reason: ${ex.message}
                        """
                throw TimeoutException(message)
            }
            Thread.sleep(poolingInterval)
        } catch (ex: org.openqa.selenium.NoSuchElementException) {
            if (System.currentTimeMillis() > endTime) {
                val message = """
            failed while waiting ${timeout / 1000} seconds
            for existence of all elements {$elements}
            reason:
                either wrong locator
                or did not have time to load
                """
                throw TimeoutException(message)
            }
            Thread.sleep(poolingInterval)
        }
    }
}

fun waitFor(element: KElement, condition: Condition, timeout: Int = 4000, poolingInterval: Long = 0.1.toLong()) {
    val endTime = System.currentTimeMillis() + timeout
    while (true) {
        try {
            return condition.evaluate(element.webElement)
        } catch (ex: ConditionMismatchException) {
            if (System.currentTimeMillis() > endTime) {
                val message = """
            failed while waiting ${timeout / 1000} seconds
            to assert $condition
            for element located {$element}
            reason: ${ex.message}
                        """
                throw TimeoutException(message)
            }
            Thread.sleep(poolingInterval)
        } catch (ex: org.openqa.selenium.NoSuchElementException) {
            if (System.currentTimeMillis() > endTime) {
                val message = """
            failed while waiting ${timeout / 1000} seconds
            for existence of element {$element}
            reason:
                either wrong locator
                or did not have time to load
                """
                throw TimeoutException(message)
            }
            Thread.sleep(poolingInterval)
        }
    }
}

