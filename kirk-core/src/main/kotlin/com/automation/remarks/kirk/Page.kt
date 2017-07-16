package com.automation.remarks.kirk

import com.automation.remarks.kirk.core.SearchContext


/**
 * Created by sergey on 24.06.17.
 */
abstract class Page(var browser: Browser) : SearchContext by browser {

    open val url: String? = null
    open val at: Browser.() -> Boolean = { true }
}
