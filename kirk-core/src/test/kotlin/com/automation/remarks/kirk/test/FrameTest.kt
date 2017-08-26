package com.automation.remarks.kirk.test

import com.automation.remarks.kirk.Kirk.Companion.drive
import com.automation.remarks.kirk.conditions.visible
import org.testng.annotations.Test

/**
 * Created by sepi on 26.08.17.
 */
class FrameTest : BaseTest() {

    @Test
    fun testCanSwitchToFrame() {
        drive {
            open(url)
            toFrame(element("#frame"))
            element(".genres").shouldBe(visible)
        }
    }

}