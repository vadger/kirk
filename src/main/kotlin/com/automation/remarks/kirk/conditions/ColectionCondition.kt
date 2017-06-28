package com.automation.remarks.kirk.conditions

import com.automation.remarks.kirk.ex.ConditionMismatchException
import org.openqa.selenium.WebElement

/**
 * Created by sergey on 28.06.17.
 */
abstract class CollectionCondition {
    fun evaluate(element: List<WebElement>) {
        match(element)
    }

    abstract fun match(element: List<WebElement>)
}

class CollectionSize(val size: Int) : CollectionCondition() {

    override fun match(element: List<WebElement>) {
        val actual = element.size
        if (actual != size) {
            throw ConditionMismatchException(actual.toString(), size.toString())
        }
    }
}

class CollectionExactText(){

}