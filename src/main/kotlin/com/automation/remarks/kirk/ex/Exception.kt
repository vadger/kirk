package com.automation.remarks.kirk.ex

/**
 * Created by sergey on 25.06.17.
 */


class ConditionMismatchException(actual: Any?, expected: Any?,
                                 override var message: String) : Exception() {init {
    message += """
                 expected: $expected
                 actual: $actual
             """
}
}