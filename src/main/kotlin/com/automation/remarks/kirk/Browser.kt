package com.automation.remarks.kirk

import com.automation.remarks.kirk.core.JsExecutor
import com.automation.remarks.kirk.core.Navigable
import com.automation.remarks.kirk.core.ScreenshotContainer
import com.automation.remarks.kirk.core.SearchContext
import com.automation.remarks.kirk.ex.WrongUrlException
import org.aeonbits.owner.ConfigFactory
import org.openqa.selenium.By
import org.openqa.selenium.Dimension
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver


class Browser(val driver: WebDriver = ChromeDriver()) : SearchContext, Navigable {

    val config: Configuration = ConfigFactory.create(Configuration::class.java, System.getProperties())

    var baseUrl: String? = null
        get() {
            if (field == null) {
                field = config.baseUrl()
            }
            return field?.removeSuffix("/")
        }

    var timeout: Int? = null
        get() {
            if (field == null) {
                field = config.timeout()
            }
            return field
        }

    var poolingInterval: Double? = null
        get() {
            if (field == null) {
                field = config.poolingInterval()
            }
            return field
        }

    var startMaximized: Boolean? = null
        get() {
            if (field == null) {
                field = config.startMaximized()
            }
            return field
        }

    var screenSize: List<Int>? = null
        get() {
            if (field == null) {
                field = config.screenSize()
            }
            return field
        }

    fun with(block: Browser.() -> Unit): Browser {
        return this.apply(block)
    }

    val currentUrl: String by lazy {
        driver.currentUrl
    }

    val js: JsExecutor = JsExecutor(driver)

    override fun open(url: String) {
        if (screenSize == null) {
            driver.manage().window().maximize()
        } else {
            driver.manage().window().size = Dimension(screenSize!![0], screenSize!![1])
        }
        if (isAbsoluteUrl(url)) {
            driver.navigate().to(url)
        } else {
            if (baseUrl == null) {
                throw WrongUrlException("Can't navigate to url [$url]. " +
                        "Please use absolute or set the base url !!!")
            }
            driver.navigate().to(baseUrl + url)
        }
    }

    private fun isAbsoluteUrl(url: String): Boolean {
        return (url.startsWith("http://") || url.startsWith("https://"))
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
        return KElement(by, driver).apply {
            waitTimeout = timeout!!
            waitPoolingInterval = poolingInterval!!
        }
    }

    override fun all(by: By): KElementCollection {
        return KElementCollection(by, driver).apply {
            waitTimeout = timeout!!
            waitPoolingInterval = poolingInterval!!
        }
    }

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