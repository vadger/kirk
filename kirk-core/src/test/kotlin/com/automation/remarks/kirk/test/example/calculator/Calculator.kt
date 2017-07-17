package com.automation.remarks.kirk.test.example.calculator

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.Page

/**
 * Created by sergey on 17.07.17.
 */
class Calculator(browser: Browser) : Page(browser) {

    override val url: String?
        get() = "http://juliemr.github.io/protractor-demo/"

    val first = element("input[ng-model='first']")
    val second = element("input[ng-model='second']")
    val goBtn = element("#gobutton")
    val result = element("h2.ng-binding")

    val select = select("select[ng-model='operator']")
}