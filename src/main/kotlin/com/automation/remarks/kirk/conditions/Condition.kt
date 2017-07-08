package com.automation.remarks.kirk.conditions

/**
 * Created by sergey on 24.06.17.
 */

abstract class Condition<T> {
    fun evaluate(element: T): T {
        return match(element)
    }

    abstract fun match(element: T): T

    abstract override fun toString(): String
}

