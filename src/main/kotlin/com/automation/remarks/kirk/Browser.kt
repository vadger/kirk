package com.automation.remarks.kirk

import org.openqa.selenium.WebDriver

/**
 * Created by sergey on 24.06.17.
 */
interface Browser {

    companion object {

        private val driverContaner = ThreadLocalDriverContainer()

        fun getDriver(): WebDriver {
            return driverContaner.getDriver()
        }

        fun drive(closure: BrowserImpl.() -> Unit) {
            BrowserImpl(getDriver()).apply {
                Runtime.getRuntime().addShutdownHook(object : Thread() {
                    override fun run() = quit()
                })
                closure()
            }
        }
    }
}