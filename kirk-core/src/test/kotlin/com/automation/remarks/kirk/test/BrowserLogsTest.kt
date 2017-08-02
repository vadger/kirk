package com.automation.remarks.kirk.test

import com.automation.remarks.kirk.Kirk
import com.automation.remarks.kirk.conditions.size
import org.openqa.selenium.logging.LogType
import org.testng.annotations.Test

/**
 * Created by alpa on 01.08.17.
 */
class BrowserLogsTest : BaseTest() {

    @Test
    fun testCanPrintJsErrorInChrome() {
        Kirk.drive {
            to(url)
            all(".list li").shouldHave(size(3))
            val logEntry = logs(LogType.BROWSER)

            for(log in logEntry) println("$log")
        }
    }

}


