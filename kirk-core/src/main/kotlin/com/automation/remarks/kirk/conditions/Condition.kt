package com.automation.remarks.kirk.conditions

import com.automation.remarks.kirk.ex.ConditionMismatchException
import org.apache.commons.lang3.StringUtils

/**
 * Created by sergey on 24.06.17.
 */

class ConditionAssert {

    companion object {
        fun <T> evaluate(item: T, matcher: Condition<T>) {
            if (!matcher.matches(item)) {
                throw ConditionMismatchException(matcher.description(item).toString())
            }
        }
    }
}

abstract class Condition<in T> {

    abstract fun matches(item: T): Boolean

    abstract fun description(item: T): Description

    override fun toString(): String {
        return StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(this.javaClass.simpleName), " ").toLowerCase()
    }
}

