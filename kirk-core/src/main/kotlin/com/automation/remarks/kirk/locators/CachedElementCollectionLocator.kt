package com.automation.remarks.kirk.locators

import com.automation.remarks.kirk.KElement
import org.openqa.selenium.WebElement

/**
 * Created by sepi on 12.07.17.
 */
class CachedElementCollectionLocator(private val list: List<KElement>, private val label: String) : ElementLocator<List<WebElement>> {
    override fun find(): List<WebElement> {
        return list.map { it.webElement }
    }

    override val description: String
        get() = label
}