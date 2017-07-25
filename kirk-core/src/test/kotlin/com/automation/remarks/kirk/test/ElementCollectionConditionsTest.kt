package com.automation.remarks.kirk.test

import com.automation.remarks.kirk.Kirk
import com.automation.remarks.kirk.conditions.have
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
            }
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
}