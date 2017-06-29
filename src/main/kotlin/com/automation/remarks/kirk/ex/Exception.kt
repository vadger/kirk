package com.automation.remarks.kirk.ex

/**
 * Created by sergey on 25.06.17.
 */
class ConditionMismatchException(actual: String,
                                 expected: String,
                                 override var message: String = "condition did not match") : Exception() {init {
    message += """
                expected: $expected
                actual: $actual
            """
}
}