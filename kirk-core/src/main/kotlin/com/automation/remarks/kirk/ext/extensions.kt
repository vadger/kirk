@file: JvmName("Extensions")

package com.automation.remarks.kirk.ext

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.Configuration
import com.automation.remarks.kirk.KElement
import com.automation.remarks.kirk.Kirk
import com.automation.remarks.kirk.core.Select
import com.automation.remarks.kirk.core.configuration
import com.automation.remarks.kirk.core.loadConfig
import com.automation.remarks.kirk.core.screenshots
import org.apache.commons.io.FileUtils
import org.openqa.selenium.*
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.remote.UnreachableBrowserException
import java.io.File
import kotlin.reflect.KClass

/**
 * Created by sergey on 09.07.17.
 */
@JvmOverloads
fun WebDriver.autoClose(enabled: Boolean = true): WebDriver {
    if (enabled) {
        Runtime.getRuntime().addShutdownHook(object : Thread() {
            override fun run() = quit()
        })
    }
    return this
}

fun WebDriver.saveScreenshot(path: String = "${System.getProperty("user.dir")}/build/reports/screen_${System.currentTimeMillis()}.png"): File {
    val scrFile = (this as TakesScreenshot).getScreenshotAs(OutputType.FILE)
    val screenshot = File(path)
    FileUtils.copyFile(scrFile, screenshot)
    screenshots[Thread.currentThread().id] = screenshot
    return screenshot
}

fun WebDriver.isAlive(): Boolean {
    try {
        title
        return true
    } catch (e: UnreachableBrowserException) {
        return false
    } catch (e: NoSuchWindowException) {
        return false
    } catch (e: NoSuchSessionException) {
        return false
    }
}

val WebElement.classes: List<String>
    get() = this.getAttribute("class").split(" ")

fun <T : Configuration> Kirk.Companion.withConfig(klazz: KClass<T>): Kirk.Companion {
    configuration = loadConfig(klazz)
    return this
}

fun Browser.select(cssLocator: String): Select {
    return select(By.cssSelector(cssLocator))
}

fun Browser.select(by: By): Select {
    return Select(element(by))
}

fun Actions.hover(element: KElement) {
    this.moveToElement(element.webElement)
}

fun Actions.click(element: KElement) {
    this.click(element.webElement)
}