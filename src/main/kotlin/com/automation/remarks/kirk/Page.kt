package com.automation.remarks.kirk

import org.openqa.selenium.By

/**
 * Created by sergey on 24.06.17.
 */
open class Page(var browser: Browser = Browser()) {

    open val url: String? = null

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
}