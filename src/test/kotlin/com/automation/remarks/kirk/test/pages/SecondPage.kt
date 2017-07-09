package com.automation.remarks.kirk.test.pages

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.Page

/**
 * Created by sergey on 09.07.17.
 */
class SecondPage(browser: Browser) : Page(browser) {
    val header = element("#header")
}