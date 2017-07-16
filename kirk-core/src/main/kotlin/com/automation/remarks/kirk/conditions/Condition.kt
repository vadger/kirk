package com.automation.remarks.kirk.conditions

import org.apache.commons.lang3.StringUtils

/**
 * Created by sergey on 24.06.17.
 */

abstract class Condition<T> {
    fun evaluate(element: T): T {
        return match(element)
    }

    abstract fun match(element: T): T

    override fun toString(): String {
        return StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(this.javaClass.simpleName), " ").toLowerCase()
    }
}

