package com.automation.remarks.kirk.core

import com.automation.remarks.kirk.KElement
import com.automation.remarks.kirk.ex.ConditionMismatchException
import com.automation.remarks.kirk.ex.DiffExtractor
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ISelect
import org.openqa.selenium.support.ui.Select

/**
 * Created by sepi on 6/30/2017.
 */
/**
 * Shows a value in a failure message.
 */
fun show(value: Any?): String = "<${display(value)}>"

fun display(value: Any?): String {
    return when (value) {
        null -> "null"
        is String -> "$value"
        is Class<*> -> value.name
        is Array<*> -> value.joinToString(prefix = "[", postfix = "]", transform = ::display)
        is Regex -> "/$value/"
        else -> value.toString()
    }
}

/**
 * Fails an assert with the given expected and actual values.
 */
//fun fail(expected: Any?, actual: Any?,
//         message: String = "condition did not match", withDiff: Boolean = true): ConditionMismatchException {
//    if (!withDiff) {
//        return ConditionMismatchException(actual, expected, message)
//    }
//    if (expected == null || actual == null || expected == actual) {
//        return ConditionMismatchException(show(actual), show(expected), message)
//    } else {
//        val extractor = DiffExtractor(display(expected), display(actual))
//        val prefix = extractor.compactPrefix()
//        val suffix = extractor.compactSuffix()
//        return ConditionMismatchException("$prefix${extractor.actualDiff()}$suffix",
//                "$prefix${extractor.expectedDiff()}$suffix",
//                message)
//    }
//}

class Select(private val element: KElement) {

    private val select: Select
        get() = Select(element.webElement)

    val options get() = select.options

    val isMultiple get() = select.isMultiple

    val firstSelectedOption get() = select.firstSelectedOption

    val allSelectedOptions get() = select.allSelectedOptions

    fun selectOption(text: String) = select.selectByVisibleText(text)

    fun selectOption(index: Int) = select.selectByIndex(index)

    fun deselect(index: Int) = select.deselectByIndex(index)

    fun selectOptionByValue(value: String?) = select.selectByValue(value)

    fun deselect(text: String) = select.deselectByVisibleText(text)

    fun deselectOptionByValue(value: String?) = select.deselectByValue(value)

    fun deselectAll() = select.deselectAll()
}
