package com.automation.remarks.kirk

/**
 * Created by sergey on 02.07.17.
 */
class PageNavigator {

    fun <T : Page> thanAt(pageClass: () -> T, closure: T.() -> Unit) {
        return pageClass().closure()
    }
}