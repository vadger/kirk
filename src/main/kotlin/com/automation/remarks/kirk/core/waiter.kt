package com.automation.remarks.kirk.core

import com.automation.remarks.kirk.conditions.Condition
import com.automation.remarks.kirk.ex.ConditionMismatchException
import com.automation.remarks.kirk.locators.ElementLocator
import org.openqa.selenium.TimeoutException
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement


/**
 * Created by sergey on 25.06.17.
 */
fun <T> waitFor(driver: WebDriver,
                locator: ElementLocator<T>,
                condition: Condition<T>,
                timeout: Int,
                poolingInterval: Double) {

    val endTime = System.currentTimeMillis() + timeout
    val screen = ScreenshotContainer(driver)
    while (true) {
        try {
            return condition.evaluate(locator.find())
        } catch (ex: ConditionMismatchException) {
            if (System.currentTimeMillis() > endTime) {
                highlightElement(driver, locator)

                val message = """
            failed while waiting ${timeout / 1000} seconds
            to assert $condition
            for element located {${locator.description}}
            reason: ${ex.message}
            screenshot: file://${screen.takeScreenshotAsFile()?.absolutePath}
                        """
                throw TimeoutException(message)
            }
            Thread.sleep(poolingInterval.toLong())
        } catch (ex: org.openqa.selenium.NoSuchElementException) {
            if (System.currentTimeMillis() > endTime) {
                val message = """
            failed while waiting ${timeout / 1000} seconds
            for existence of element {${locator.description}}
            reason: no such element
                either wrong locator
                or did not have time to load

                screenshot: file://${screen.takeScreenshotAsFile()?.absolutePath}
                """
                throw TimeoutException(message)
            }
            Thread.sleep(poolingInterval.toLong())
        }
    }
}

private fun <T> highlightElement(driver: WebDriver, locator: ElementLocator<T>) {
    val element = locator.find()
    when (element) {
        is List<*> -> {
            for (el in element) {
                highlightElement(driver, el as WebElement)
            }
        }
        is WebElement -> highlightElement(driver, element)
    }
}

private fun highlightElement(driver: WebDriver, element: WebElement) {
    JsExecutor(driver).execute(element) {
        "arguments[0].style.setProperty('border', '2px dotted red');"
    }
}

fun sleep(i: Long) {
    Thread.sleep(i)
}