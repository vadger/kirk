package com.automation.remarks.kirk

import com.automation.remarks.kirk.conditions.Condition
import com.automation.remarks.kirk.ex.ConditionMismatchException
import com.automation.remarks.kirk.locators.ElementLocator
import org.openqa.selenium.TimeoutException

/**
 * Created by sergey on 25.06.17.
 */
//fun waitFor(elements: KElementCollection, condition: CollectionCondition, timeout: Int = 4000, poolingInterval: Long = 0.1.toLong()): Boolean {
//    val endTime = System.currentTimeMillis() + timeout
//    while (true) {
//        try {
//            return condition.evaluate(elements.webElements)
//        } catch (ex: ConditionMismatchException) {
//            if (System.currentTimeMillis() > endTime) {
//                val message = """
//            failed while waiting ${timeout / 1000} seconds
//            to assert $condition
//            for all elements located {$elements}
//            reason: ${ex.message}
//                        """
//                throw TimeoutException(message)
//            }
//            Thread.sleep(poolingInterval)
//        } catch (ex: org.openqa.selenium.NoSuchElementException) {
//            if (System.currentTimeMillis() > endTime) {
//                val message = """
//            failed while waiting ${timeout / 1000} seconds
//            for existence of all elements {$elements}
//            reason:
//                either wrong locator
//                or did not have time to load
//                """
//                throw TimeoutException(message)
//            }
//            Thread.sleep(poolingInterval)
//        }
//    }
//}

fun <T> waitFor(locator: ElementLocator<T>, condition: Condition<T>, timeout: Int = 4000, poolingInterval: Long = 0.1.toLong()) {
    val endTime = System.currentTimeMillis() + timeout
    while (true) {
        try {
            return condition.evaluate(locator.find())
        } catch (ex: ConditionMismatchException) {
            if (System.currentTimeMillis() > endTime) {
                val message = """
            failed while waiting ${timeout / 1000} seconds
            to assert $condition
            for element located {${locator.description}}
            reason: ${ex.message}
                        """
                throw TimeoutException(message)
            }
            Thread.sleep(poolingInterval)
        } catch (ex: org.openqa.selenium.NoSuchElementException) {
            if (System.currentTimeMillis() > endTime) {
                val message = """
            failed while waiting ${timeout / 1000} seconds
            for existence of element {${locator.description}}
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

