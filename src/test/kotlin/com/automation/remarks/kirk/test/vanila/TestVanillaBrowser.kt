package com.automation.remarks.kirk.test.vanila

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.conditions.have
import com.automation.remarks.kirk.core.drive
import com.automation.remarks.kirk.test.BaseTest
import com.automation.remarks.kirk.test.pages.SecondPage
import com.automation.remarks.kirk.test.pages.StartPage
import org.testng.annotations.Test

/**
 * Created by sergey on 09.07.17.
 */
class TestVanillaBrowser : BaseTest() {

    @Test fun testCanDriverBrowser() {
        Browser.drive {
            screenSize = listOf(640, 480)
            open(url)
            element("#header").should(have.text("Kirk"))
        }
    }

    @Test fun testCanDriverPage() {
        Browser.drive {
            baseUrl = url
            to(::StartPage) {
                list should(have.size(3))
                link.click()
                at(::SecondPage).header.should(have.text("Second page"))
            }
        }
    }
}