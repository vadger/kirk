package com.automation.remarks.kirk

import org.openqa.selenium.WebDriver

/**
 * Created by sergey on 02.07.17.
 */
class Navigator(private val browser: Browser) {

    private val driver: WebDriver
        get() = browser.driver

    fun <T : Page> at(pageClass: (Browser) -> T, closure: T.() -> Unit) {
        val page = pageClass(browser)
        page.closure()
    }

    private fun isAbsoluteUrl(url: String): Boolean {
        return (url.startsWith("http://") || url.startsWith("https://"))
    }



//    fun open(url: String) {
//        if (config.startMaximized()) {
//            driver.manage().window().maximize()
//        } else {
//            val screenSize = config.screenSize()
//            driver.manage().window().size = Dimension(screenSize[0], screenSize[1])
//        }
//        if (isAbsoluteUrl(url)) {
//            driver.navigate().open(url)
//        } else {
//            val baseUrl = config.baseUrl() ?:
//                    throw IllegalStateException("Base url can't be null")
//            driver.navigate().open(baseUrl + url)
//        }
//        if (config.autoClose()) {
//            addAutoCloseHook()
//        }
//    }

//    private fun addAutoCloseHook() {
//        Runtime.getRuntime().addShutdownHook(object : Thread() {
//            override fun run() = quit()
//        })
//    }
}