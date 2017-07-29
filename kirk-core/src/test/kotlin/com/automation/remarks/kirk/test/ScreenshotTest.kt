package com.automation.remarks.kirk.test

import com.automation.remarks.kirk.Kirk
import com.automation.remarks.kirk.core.getLatestScreenshot
import me.tatarka.assertk.assertions.isEqualTo
import org.testng.annotations.Test

/**
 * Created by sergey on 29.07.17.
 */
class ScreenshotTest : BaseTest() {

    @Test
    fun testCanTakeScreenshot() {
        Kirk.drive {
            to(url)
            val file = takeScreenshot()
            val cached = getLatestScreenshot()
            me.tatarka.assertk.assert(file).isEqualTo(cached)
        }
    }
}