package com.automation.remarks.kirk

import com.automation.remarks.kirk.conditions.Condition
import com.automation.remarks.kirk.core.waitFor
import com.automation.remarks.kirk.locators.ElementLocator
import org.openqa.selenium.WebDriver

/**
 * Created by sergey on 09.07.17.
 */
abstract class Element<T>(protected val locator: ElementLocator<T>,
                          protected val driver: WebDriver) {
    var waitTimeout: Int = 4000
    var waitPoolingInterval: Double = 0.1

    protected fun should(condition: Condition<T>) {
        waitFor(driver, this.locator, condition, waitTimeout, waitPoolingInterval)
    }
}