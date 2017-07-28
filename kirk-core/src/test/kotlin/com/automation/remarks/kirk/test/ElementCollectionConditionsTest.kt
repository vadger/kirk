package com.automation.remarks.kirk.test

import com.automation.remarks.kirk.Kirk
import com.automation.remarks.kirk.conditions.be
import com.automation.remarks.kirk.conditions.have
import com.automation.remarks.kirk.ext.autoClose
import me.tatarka.assertk.assertions.hasClass
import me.tatarka.assertk.assertions.hasMessageStartingWith
import org.openqa.selenium.TimeoutException
import org.testng.annotations.Test

/**
 * Created by sergey on 24.07.17.
 */
class ElementCollectionConditionsTest : BaseTest() {

    @Test
    fun testNotCondition() {
        me.tatarka.assertk.assert {
            Kirk.drive {
                to(url)
                all(".list li").shouldNot(have.elementWithText("Один"))
            }.driver.autoClose(true)
        }.throwsError {
            it.hasClass(TimeoutException::class)
            it.hasMessageStartingWith("""
            failed while waiting 4 seconds
            to assert collection have element with text
            for collection located {By.cssSelector: .list li}
            reason: condition did not match
                expected not: Один
                actual: [Один, Два, Три]
            """)
        }
    }

    @Test
    fun testCollectionMinimumSizCondition() {
        me.tatarka.assertk.assert {
            Kirk.drive {
                to(url)
                all("li")[20].should(be.visible)
            }.driver.autoClose(true)
        }.throwsError {
            it.hasClass(TimeoutException::class)
            it.hasMessageStartingWith("""
            failed while waiting 4 seconds
            to assert collection minimum size
            for collection located {By.cssSelector: li}
            reason: required index 9 exceeds collections size
                expected: [20]
                actual: [9]
            """)
        }
    }
}