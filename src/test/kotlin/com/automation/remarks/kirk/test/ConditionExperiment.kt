package com.automation.remarks.kirk.test

import com.automation.remarks.kirk.core.display
import com.automation.remarks.kirk.ex.DiffExtractor

/**
 * Created by sergey on 23.07.17.
 */
data class User(val name: String, val age: Int)

class ConditionMismatch() {
    var message = """
        """
}

abstract class Condition<in T> {
    abstract fun matches(item: T): Boolean

    abstract fun description(item: T): String

    fun describe(actual: Any, expected: Any): String {
        val extractor = DiffExtractor(display(expected), display(actual))
        val prefix = extractor.compactPrefix()
        val suffix = extractor.compactSuffix()

        return """
            actual: $prefix${extractor.actualDiff()}$suffix
            expected: "$prefix${extractor.expectedDiff()}$suffix
            """
    }
}

class ConditionAssert<T> {
    fun evaluate(item: T, matcher: Condition<T>) {
        if (!matcher.matches(item)) {
            println(matcher.description(item))
        }
    }
}

fun name(expected: String): Condition<User> {
    return object : Condition<User>() {
        override fun description(item: User): String {
            return describe(item.name, expected)
        }

        override fun matches(item: User): Boolean = item.name == expected
    }
}

fun User.shouldHave(condition: Condition<User>) {
    ConditionAssert<User>().evaluate(this, condition)
}

fun main(arg: Array<String>) {
    User("Ivan", 28).shouldHave(name("van"))
}