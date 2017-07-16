package com.automation.remarks.kirk.test.pages

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.Page

/**
 * Created by sergey on 09.07.17.
 */
class SecondPage(browser: Browser) : Page(browser) {

    override val at: Browser.() -> Boolean = { title == "Kirk Test Page 2" }

    val header = element("#header")
}