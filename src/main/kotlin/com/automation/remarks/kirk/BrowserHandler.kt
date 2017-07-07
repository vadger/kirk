package com.automation.remarks.kirk

import com.automation.remarks.kirk.core.JsExecutor
import com.automation.remarks.kirk.core.ScreenshotContainer
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver



class BrowserHandler(val driver: WebDriver) : Browser {

    private val navigator = Navigator(this)

    override fun to(url: String) {
        navigator.to(url)
    }

    fun <T : Page> to(pageClass: () -> T): T {
        val page = pageClass()
        page.browser = this
        page.url?.let { to(it) }
        return page
    }

    override fun <T : Page> to(pageClass: () -> T, block: T.() -> Unit): Navigator {
        val page = to(pageClass)
        page.block()
        return navigator
    }


    override fun element(by: By): KElement {
        return KElement(by, driver)
    }

    override fun all(by: By): KElementCollection {
        return KElementCollection(by, driver)
    }

    override val currentUrl: String by lazy {
        driver.currentUrl
    }

    override val js: JsExecutor = JsExecutor(driver)

    fun takeScreenshot(saveTo: String = "${System.getProperty("user.dir")}/build/screen_${System.currentTimeMillis()}.png") {
        ScreenshotContainer(driver, saveTo).takeScreenshotAsFile()
    }
}