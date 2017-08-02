package com.automation.remarks.kirk.test

import com.automation.remarks.kirk.Kirk
import com.automation.remarks.kirk.conditions.size
import org.testng.annotations.Test

/**
 * Created by alpa on 01.08.17.
 */
class JsErrorTest : BaseTest() {

    @Test
    fun testCanPrintJsErrorInChrome() {
        Kirk.drive {
            to(url)
            all(".list li").shouldHave(size(3))
            extractJSLogs()
        }
    }

}


