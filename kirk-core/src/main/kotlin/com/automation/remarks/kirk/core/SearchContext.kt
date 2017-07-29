package com.automation.remarks.kirk.core

import com.automation.remarks.kirk.KElement
import com.automation.remarks.kirk.KElementCollection
import org.openqa.selenium.By

/**
 * Created by sergey on 09.07.17.
 */
interface SearchContext {

    fun element(cssLocator: String) = element(By.cssSelector(cssLocator))

    fun element(by: By): KElement

    fun all(cssLocator: String) = all(By.cssSelector(cssLocator))

    fun all(by: By): KElementCollection
}
