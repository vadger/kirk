package com.automation.remarks.kirk

import com.automation.remarks.kirk.core.*
import com.automation.remarks.kirk.ext.saveScreenshot
import org.openqa.selenium.Alert
import org.openqa.selenium.By
import org.openqa.selenium.Dimension
import org.openqa.selenium.WebDriver
import org.openqa.selenium.interactions.Actions
import java.io.File

class Browser(val driver: WebDriver = getDriver(),
              val listener: EventListener = AbstractEventListener()) : SearchContext, Navigable {

    init {
        listener.onStart()
    }

    var config: Configuration = loadConfig(Configuration::class)

    var baseUrl: String by baseUrl()

    var timeout: Int by timeout()

    var poolingInterval: Double by poolingInterval()

    var startMaximized: Boolean? by startMaximized()

    var screenSize: List<Int> by screenSize()

    val actions = Actions(driver)

    fun with(block: Browser.() -> Unit): Browser {
        return this.apply(block)
    }

    val currentUrl: String get() = driver.currentUrl

    val js: JsExecutor by lazy { JsExecutor(driver) }

    override fun open(url: String) {
        listener.beforeNavigation(url, driver)
        if (screenSize.isNotEmpty()) {
            driver.manage().window().size = Dimension(screenSize[0], screenSize[1])
        } else if (startMaximized!!) {
            driver.manage().window().maximize()
        }

        if (isAbsoluteUrl(url)) {
            driver.navigate().to(url)
        } else {
            driver.navigate().to(baseUrl + url)
        }
        listener.afterNavigation(url, driver)
    }

    private fun isAbsoluteUrl(url: String): Boolean {
        return (url.startsWith("http://") || url.startsWith("https://"))
    }

    fun to(url: String) {
        open(url)
    }

    fun interact(block: Actions.() -> Unit) {
        this.actions.apply(block).build().perform()
    }

    override fun <T : Page> to(pageClass: (Browser) -> T): T {
        val page = pageClass(this)
        page.url?.let { open(it) }
        return page
    }

    fun <T : Page> at(pageClass: (Browser) -> T): T {
        val page = pageClass(this)
        assert(page.isAt(this))
        return page
    }

    override fun <T : Page> at(pageClass: (Browser) -> T, closure: T.() -> Unit): T {
        val page = pageClass(this)
        page.closure()
        return page
    }

    override fun element(by: By): KElement {
        listener.beforeElementLocation(by, driver)
        return KElement(by, driver).apply {
            waitTimeout = timeout
            waitPoolingInterval = poolingInterval
            eventListener = listener
        }
    }

    override fun all(by: By): KElementCollection {
        return KElementCollection(by, driver).apply {
            waitTimeout = timeout
            waitPoolingInterval = poolingInterval
        }
    }

    fun takeScreenshot(saveTo: String = "${System.getProperty("user.dir")}/build/reports/screen_${System.currentTimeMillis()}.png"): File {
        val file = driver.saveScreenshot(saveTo)
        screenshots.put(Thread.currentThread().id, file)
        return file
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

    fun scrollTo(element: KElement) {
        js.execute(element.webElement) { "arguments[0].scrollIntoView();" }
    }

    override fun quit() {
        driver.quit()
    }

    val alert: Alert
        get() = driver.switchTo().alert()

    val title: String
        get() = driver.title
}