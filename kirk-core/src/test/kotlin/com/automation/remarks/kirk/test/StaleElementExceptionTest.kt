package com.automation.remarks.kirk.test

import com.automation.remarks.kirk.Kirk.Companion.drive
import me.tatarka.assertk.assertions.isEqualTo
import org.testng.annotations.Test

class StaleElementExceptionTest : BaseTest() {

    @Test
    fun testCanHandleStaleElementException() {
        drive {
            to(url)
            val button = all("input[type='button']")[2]
            refresh()
            button.click()
            val text = alert.text
            alert.dismiss()
            me.tatarka.assertk.assert(text).isEqualTo("Добрый день")
        }
    }
}