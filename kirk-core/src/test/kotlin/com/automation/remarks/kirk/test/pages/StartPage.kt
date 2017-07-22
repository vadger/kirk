package com.automation.remarks.kirk.test.pages

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.Page

/**
 * Created by sergey on 09.07.17.
 */
class StartPage(browser: Browser) : Page(browser) {
    override val url: String?
        get() = "/"

    val header = element("#header")
    val list = element(".list").all("li")
    val link = element(".paginator a")
    val confirmBtn = element("#confirm_btn")
}

