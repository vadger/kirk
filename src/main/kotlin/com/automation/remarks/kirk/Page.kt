package com.automation.remarks.kirk

import com.automation.remarks.kirk.core.DriverlessBrowser
import com.automation.remarks.kirk.core.JsExecutor
import org.openqa.selenium.By

/**
 * Created by sergey on 24.06.17.
 */
abstract class Page(var browser: Browser = DriverlessBrowser()) {

    open val url: String? = null

    fun to(url: String) {
        browser.to(url)
    }

    fun <T : Page> to(pageClass: () -> T, block: T.() -> Unit): Navigator {
        return browser.to(pageClass, block)
    }

    fun element(byCss: String): KElement {
        return browser.element(byCss)
    }

    fun element(by: By): KElement {
        return browser.element(by)
    }

    fun all(byCss: String): KElementCollection {
        return browser.all(byCss)
    }

    fun all(by: By): KElementCollection {
        return browser.all(by)
    }

    val currentUrl: String
        get() = browser.currentUrl
    val js: JsExecutor
        get() = browser.js
}
