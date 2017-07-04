package com.automation.remarks.kirk.core

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.KElement
import com.automation.remarks.kirk.KElementCollection
import org.openqa.selenium.By

/**
 * Created by sergey on 02.07.17.
 */
interface IBrowser {

    fun to(url: String): Browser

    fun element(byCss: String): KElement {
        return element(By.cssSelector(byCss))
    }

    fun element(by: By): KElement

    fun all(byCss: String): KElementCollection

    fun all(by: By): KElementCollection

    val currentUrl: String

    val js: JsExecutor
}