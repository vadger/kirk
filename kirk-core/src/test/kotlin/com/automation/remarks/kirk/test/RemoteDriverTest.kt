package com.automation.remarks.kirk.test

import com.automation.remarks.kirk.Kirk.Companion.drive
import com.automation.remarks.kirk.conditions.text
import me.tatarka.assertk.assertions.hasClass
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

/**
 * Created by sergey on 10.08.17.
 */
class RemoteDriverTest : BaseTest() {

    @BeforeClass
    fun setUp() {
        System.setProperty("kirk.remote.url", "http://localhost:4444/wd/hub")
    }

    @Test
    fun testCanInitRemoteDriver() {
        me.tatarka.assertk.assert {
            drive {
                open(url)
                element("#header").shouldHave(text("Kirk"))
            }
        }.throwsError {
            it.hasClass(ExceptionInInitializerError::class)
        }
    }
}