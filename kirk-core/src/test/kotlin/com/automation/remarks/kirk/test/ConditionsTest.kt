package com.automation.remarks.kirk.test

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.conditions.be
import com.automation.remarks.kirk.conditions.have
import me.tatarka.assertk.assertions.hasClass
import me.tatarka.assertk.assertions.hasMessageStartingWith
import org.openqa.selenium.TimeoutException
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

/**
 * Created by sepi on 7/12/2017.
 */
class ConditionsTest : BaseTest() {

    lateinit var chrome: Browser

    @BeforeClass
    fun setUp() {
        chrome = Browser().with {
            baseUrl = url
        }
        chrome.open(url)
    }

    @Test fun testTextCondition() {
        chrome.element("#header").should(have.text("Kirk"))
    }

    @Test fun testTextConditionFailMassage() {
        me.tatarka.assertk.assert {
            chrome.element("#header").should(have.text("irk"))
        }.throwsError {
            it.hasClass(TimeoutException::class)
            it.hasMessageStartingWith("""
            failed while waiting 4 seconds
            to assert text
            for element located {By.cssSelector: #header}
            reason: condition did not match
                expected: []irk
                actual: [K]irk
            """)
        }
    }

    @Test fun testElementVisibilityConditionFailMassage() {
        me.tatarka.assertk.assert {
            chrome.element("#input_invisible").should(be.visible)
        }.throwsError {
            it.hasClass(TimeoutException::class)
            it.hasMessageStartingWith("""
            failed while waiting 4 seconds
            to assert element visibility
            for element located {By.cssSelector: #input_invisible}
            reason: condition did not match
                expected: visible
                actual: invisible
            """)
        }
    }

    @Test fun testElementAttrConditionFailMassage() {
        me.tatarka.assertk.assert {
            chrome.element(".paginator a").should(have.attr("href", "second_page.html"))
        }.throwsError {
            it.hasClass(TimeoutException::class)
            it.hasMessageStartingWith("""
            failed while waiting 4 seconds
            to assert attribute value {href}
            for element located {By.cssSelector: .paginator a}
            reason: condition did not match
                expected: []second_page.html
                actual: [http://localhost:32941/]second_page.html
            """)
        }
    }

}