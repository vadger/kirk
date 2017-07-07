package com.automation.remarks.kirk

/**
 * Created by sergey on 24.06.17.
 */
abstract class Page(var browser: Browser) : Browser by browser {

    open val url: String? = null
}
