package com.automation.remarks.kirk.core

import com.automation.remarks.kirk.KElement
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select

/**
 * Created by sepi on 6/30/2017.
 */
class Select(private val element: KElement) {

    private val select: Select
        get() = Select(element.webElement)

    val options: MutableList<WebElement> get() = select.options

    val isMultiple get() = select.isMultiple

    val firstSelectedOption get() = select.firstSelectedOption

    val allSelectedOptions: MutableList<WebElement> get() = select.allSelectedOptions

    fun selectOption(text: String) = select.selectByVisibleText(text)

    fun selectOption(index: Int) = select.selectByIndex(index)

    fun deselect(index: Int) = select.deselectByIndex(index)

    fun selectOptionByValue(value: String?) = select.selectByValue(value)

    fun deselect(text: String) = select.deselectByVisibleText(text)

    fun deselectOptionByValue(value: String?) = select.deselectByValue(value)

    fun deselectAll() = select.deselectAll()

    override fun toString(): String {
        return element.toString()
    }
}
