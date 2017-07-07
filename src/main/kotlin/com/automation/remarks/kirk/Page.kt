package com.automation.remarks.kirk

import com.automation.remarks.kirk.core.DriverlessBrowser
import com.automation.remarks.kirk.core.JsExecutor
import org.openqa.selenium.By

/**
 * Created by sergey on 24.06.17.
 */
abstract class Page(var browser: Browser = DriverlessBrowser()) : Browser {

    open val url: String? = null

    override fun to(url: String) {
        browser.to(url)
    }

    override fun <T : Page> to(pageClass: () -> T, block: T.() -> Unit): Navigator {
        return browser.to(pageClass, block)
    }

    override fun element(byCss: String): KElement {
        return browser.element(byCss)
    }

    override fun element(by: By): KElement {
        return browser.element(by)
    }

    override fun all(byCss: String): KElementCollection {
        return browser.all(byCss)
    }

    override fun all(by: By): KElementCollection {
        return browser.all(by)
    }

    override val currentUrl: String
        get() = browser.currentUrl
    override val js: JsExecutor
        get() = browser.js
}