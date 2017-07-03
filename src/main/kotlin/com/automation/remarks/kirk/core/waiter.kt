package com.automation.remarks.kirk.core

import com.automation.remarks.kirk.conditions.Condition
import com.automation.remarks.kirk.ex.ConditionMismatchException
import com.automation.remarks.kirk.locators.ElementLocator
import org.apache.commons.io.FileUtils
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.TimeoutException
import org.openqa.selenium.WebDriver
import java.io.File


/**
 * Created by sergey on 25.06.17.
 */
fun <T> waitFor(driver: WebDriver, locator: ElementLocator<T>, condition: Condition<T>, timeout: Int = 4000, poolingInterval: Long = 0.1.toLong()) {
    val endTime = System.currentTimeMillis() + timeout
    while (true) {
        try {
            return condition.evaluate(locator.find())
        } catch (ex: ConditionMismatchException) {
            if (System.currentTimeMillis() > endTime) {
                val screenShotPath = "${System.getProperty("user.dir")}/build/screen_${System.currentTimeMillis()}.png"
                val scrFile = (driver as TakesScreenshot).getScreenshotAs(OutputType.FILE)
                FileUtils.copyFile(scrFile, File(screenShotPath))
                val message = """
            failed while waiting ${timeout / 1000} seconds
            to assert $condition
            for element located {${locator.description}}
            reason: ${ex.message}
            screenshot: file://$screenShotPath
                        """
                throw TimeoutException(message)
            }
            Thread.sleep(poolingInterval)
        } catch (ex: org.openqa.selenium.NoSuchElementException) {
            if (System.currentTimeMillis() > endTime) {
                val message = """
            failed while waiting ${timeout / 1000} seconds
            for existence of element {${locator.description}}
            reason: no such element
                either wrong locator
                or did not have time to load
                """
                throw TimeoutException(message)
            }
            Thread.sleep(poolingInterval)
        }
    }
}

fun sleep(i: Long) {
    Thread.sleep(i)
}