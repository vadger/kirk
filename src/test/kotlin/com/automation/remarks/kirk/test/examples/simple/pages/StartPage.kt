package com.automation.remarks.kirk.test.examples.simple.pages

import com.automation.remarks.kirk.Page

/**
 * Created by sergey on 26.06.17.
 */
class StartPage : Page() {

    override val url: String?
        get() = "http://localhost:32943/"

    val header by lazy { element("#header") }
}

