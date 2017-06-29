package com.automation.remarks.kirk

import org.openqa.selenium.By

/**
 * Created by sergey on 24.06.17.
 */
open class Page(var browser: Browser = Browser()) {

    open val url: String? = null

    fun element(locator: String): KElement {
        return browser.element(locator)
    }

    fun element(locator: By): KElement {
        return browser.element(locator)
    }

    fun all(locator: String): KElementCollection {
        return browser.all(locator)
    }

    fun all(locator: By): KElementCollection {
        return browser.all(locator)
    }
}