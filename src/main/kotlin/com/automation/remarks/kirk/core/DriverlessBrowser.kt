package com.automation.remarks.kirk.core

import com.automation.remarks.kirk.*
import org.openqa.selenium.By

/**
 * Created by sergey on 07.07.17.
 */
internal class DriverlessBrowser : Browser {
    override fun <T : Page> to(pageClass: () -> T, block: T.() -> Unit): Navigator {
        throw UnsupportedOperationException()
    }

    override val currentUrl: String
        get() = throw UnsupportedOperationException()
    override val js: JsExecutor
        get() = throw UnsupportedOperationException()

    override fun to(url: String) {}

    override fun element(by: By): KElement {
        throw UnsupportedOperationException("Please declare WebElement property as lazy")
    }

    override fun all(by: By): KElementCollection {
        throw UnsupportedOperationException("Please declare ElementsCollections as lazy")
    }
}