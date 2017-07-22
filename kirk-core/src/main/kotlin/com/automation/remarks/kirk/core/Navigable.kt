package com.automation.remarks.kirk.core

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.Page

/**
 * Created by sergey on 09.07.17.
 */
interface Navigable {

    fun open(url: String)

    fun <T : Page> to(pageClass: (Browser) -> T): T

    fun <T : Page> to(pageClass: (Browser) -> T, block: T.() -> Unit) {
        val page = to(pageClass)
        page.block()
    }

    fun <T : Page> at(pageClass: (Browser) -> T, closure: T.() -> Unit = {}): T

    fun back(): Browser

    fun forward(): Browser

    fun refresh(): Browser

    fun quit()
}