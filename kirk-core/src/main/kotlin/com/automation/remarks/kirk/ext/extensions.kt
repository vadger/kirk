@file: JvmName("Extensions")

package com.automation.remarks.kirk.ext

import com.automation.remarks.kirk.*
import com.automation.remarks.kirk.core.Select
import com.automation.remarks.kirk.core.configuration
import com.automation.remarks.kirk.core.loadConfig
import org.openqa.selenium.*
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.remote.UnreachableBrowserException
import java.io.File
import kotlin.reflect.KClass

/**
 * Created by sergey on 09.07.17.
 */
fun WebDriver.autoClose(enabled: Boolean = true) {
    if (enabled) {
        Runtime.getRuntime().addShutdownHook(object : Thread() {
            override fun run() = quit()
        })
    }
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

fun KElement.uploadFile(name: String) {
    val resource = Thread.currentThread().contextClassLoader.getResource(name)
    this.setValue(File(resource.toURI()).canonicalPath)
}

fun KElement.pressEnter() {
    sendKeys(Keys.ENTER)
}

fun KElement.children(locator: String = "*"): KElementCollection {
    return this.all(locator)
}

fun KElement.firstChild(): KElement {
    return this.element(":first-child")
}

fun KElement.lastChild(): KElement {
    return this.element(":last-child")
}

fun KElement.parent(): KElement {
    return this.element(By.xpath(".."))
}

fun Actions.hover(element: KElement) {
    this.moveToElement(element.webElement)
}

fun Actions.click(element: KElement) {
    this.click(element.webElement)
}