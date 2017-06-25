package com.automation.remarks.kirk

import junit.framework.Assert

/**
 * Created by sergey on 24.06.17.
 */
open class Condition {
    open fun evaluate() {

    }
}

class Text(val text: String) : Condition() {
    fun evaluate(text: String) {
        Assert.assertEquals(text, this.text)
    }
}