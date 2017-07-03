package com.automation.remarks.kirk

import com.automation.remarks.kirk.core.IBrowser

/**
 * Created by sergey on 24.06.17.
 */
open class Page(var browser: Browser = Browser()) : IBrowser by browser {

    open val url: String? = null

}