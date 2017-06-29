package com.automation.remarks.kirk.conditions

/**
 * Created by sergey on 24.06.17.
 */

abstract class Condition<in T> {
    fun evaluate(element: T) {
        return match(element)
    }

    abstract fun match(element: T)

    abstract override fun toString(): String
}

