package com.automation.remarks.kirk.conditions

import com.automation.remarks.kirk.core.display
import com.automation.remarks.kirk.ex.ConditionMismatchException
import com.automation.remarks.kirk.ex.DiffExtractor
import org.apache.commons.lang3.StringUtils

/**
 * Created by sergey on 24.06.17.
 */

class ConditionAssert {

    companion object {
        fun <T> evaluate(item: T, matcher: Condition<T>) {
            if (!matcher.matches(item)) {
                throw ConditionMismatchException(matcher.description(item))
            }
        }
    }
}

abstract class Condition<in T> {

    val MESSAGE = """reason: condition did not match
                expected: %s
                actual: %s
            """

    abstract fun matches(item: T): Boolean

    abstract fun description(item: T): String

    fun describe(actual: Any, expected: Any, withDiff: Boolean = true): String {
        if (!withDiff) {
            return MESSAGE.format(expected, actual)
        }

        val extractor = DiffExtractor(display(expected), display(actual))
        val prefix = extractor.compactPrefix()
        val suffix = extractor.compactSuffix()

        return MESSAGE.format("$prefix${extractor.expectedDiff()}$suffix",
                "$prefix${extractor.actualDiff()}$suffix")
    }

    override fun toString(): String {
        return StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(this.javaClass.simpleName), " ").toLowerCase()
    }
}

