package com.automation.remarks.kirk.conditions

/**
 * Created by sepi on 24.07.17.
 */

abstract class BaseCondition<in T> : Condition<T>()

class Not<in T>(val condition: Condition<T>) : Condition<T>() {
    override fun matches(item: T): Boolean {
        return !condition.matches(item)
    }

    override fun description(item: T): String {
        return condition.description(item)
    }
}

fun <T> not(condition: Condition<T>): Condition<T> {
    return Not(condition)
}