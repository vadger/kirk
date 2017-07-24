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

class Select(private val element: KElement) : ISelect {

    private val select: Select
        get() = Select(element.webElement)

    override fun selectByVisibleText(text: String?) {
        select.selectByVisibleText(text)
    }

    override fun deselectByIndex(index: Int) {
        select.deselectByIndex(index)
    }

    override fun getFirstSelectedOption(): WebElement {
        return select.firstSelectedOption
    }

    override fun getAllSelectedOptions(): MutableList<WebElement> {
        return select.allSelectedOptions
    }

    override fun selectByValue(value: String?) {
        select.selectByValue(value)
    }

    override fun selectByIndex(index: Int) {
        select.selectByIndex(index)
    }

    override fun getOptions(): MutableList<WebElement> {
        return select.options
    }

    override fun deselectByVisibleText(text: String?) {
        select.deselectByVisibleText(text)
    }

    override fun deselectByValue(value: String?) {
        select.deselectByValue(value)
    }

    override fun deselectAll() {
        select.deselectAll()
    }

    override fun isMultiple(): Boolean {
        return select.isMultiple
    }
}
