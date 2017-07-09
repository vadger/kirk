package com.automation.remarks.kirk

import com.automation.remarks.kirk.core.JsExecutor
import com.automation.remarks.kirk.core.Navigable
import com.automation.remarks.kirk.core.ScreenshotContainer
import com.automation.remarks.kirk.core.SearchContext
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions


class Browser(val driver: WebDriver = ChromeDriver()) : SearchContext, Navigable {

    val mouse: Actions = Actions(driver)

    override fun open(url: String) {
        driver.navigate().to(url)
    }

    override fun <T : Page> to(pageClass: (Browser) -> T): T {
        val page = pageClass(this)
        page.url?.let { open(it) }
        return page
    }

    override fun <T : Page> at(pageClass: (Browser) -> T, closure: T.() -> Unit) {
        val page = pageClass(this)
        page.closure()
    }

    override fun element(by: By): KElement {
        return KElement(by, driver)
    }

    override fun all(by: By): KElementCollection {
        return KElementCollection(by, driver)
    }

    val currentUrl: String by lazy {
        driver.currentUrl
    }

    val js: JsExecutor = JsExecutor(driver)

    fun takeScreenshot(saveTo: String = "${System.getProperty("user.dir")}/build/screen_${System.currentTimeMillis()}.png") {
        ScreenshotContainer(driver, saveTo).takeScreenshotAsFile()
    }

    override fun back(): Browser {
        driver.navigate().back()
        return this
    }

    override fun forward(): Browser {
        driver.navigate().forward()
        return this
    }

    override fun refresh(): Browser {
        driver.navigate().refresh()
        return this
    }

    override fun quit() {
        driver.quit()
    }
}