package com.automation.remarks.kirk.core

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.Configuration
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
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