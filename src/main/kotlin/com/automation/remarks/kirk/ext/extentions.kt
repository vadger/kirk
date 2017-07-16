package com.automation.remarks.kirk.ext

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.Configuration
import com.automation.remarks.kirk.KElement
import com.automation.remarks.kirk.KElementCollection
import com.automation.remarks.kirk.core.WebDriverFactory
import com.automation.remarks.kirk.core.loadConfig
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import java.io.File
import kotlin.reflect.KClass

/**
 * Created by sergey on 09.07.17.
 */
fun WebDriver.autoClose(enabled: Boolean? = true) {
    if (!enabled!!) {
        Runtime.getRuntime().addShutdownHook(object : Thread() {
            override fun run() = quit()
        })
    }
}

val WebElement.classes: List<String>
    get() = this.getAttribute("class").split(" ")

val driverFactory = WebDriverFactory()

var configuration: Configuration = loadConfig(Configuration::class)

fun <T : Configuration> Browser.Companion.withConfig(klazz: KClass<T>): Browser.Companion {
    configuration = loadConfig(klazz)
    return this
}

fun Browser.Companion.drive(block: Browser.() -> Unit) {

    fun getDriver(): WebDriver {
        return driverFactory.getDriver()
    }

    Browser(getDriver()).with {
        config = configuration
    }.block()
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
    return this.element(By.xpath(""))
}