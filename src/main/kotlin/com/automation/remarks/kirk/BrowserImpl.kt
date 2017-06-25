package com.automation.remarks.kirk

import org.openqa.selenium.Dimension
import org.openqa.selenium.WebDriver

/**
 * Created by sergey on 24.06.17.
 */
internal class BrowserImpl(val driver: WebDriver) : Browser, WebDriver by driver {

    fun setWindowSize(screenSize: String?) {
        if (screenSize == null) {
            driver.manage().window().maximize()
        } else {
            val dimensions = screenSize.split("x")
            driver.manage().window().size = Dimension(dimensions[0].toInt(), dimensions[1].toInt())
        }
    }

    fun addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(object : Thread() {
            override fun run() = quit()
        })
    }

}