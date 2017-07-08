package com.automation.remarks.kirk.test

import com.automation.remarks.kirk.Browser.Companion.drive
import com.automation.remarks.kirk.conditions.be
import com.automation.remarks.kirk.conditions.have
import me.tatarka.assertk.assert
import me.tatarka.assertk.assertions.isEqualTo
import org.testng.annotations.Test

/**
 * Created by sergey on 24.06.17.
 */
class BrowserTest : BaseTest() {

    @Test
    fun testCanOpenUrl() {
        drive {
            to(url)
            assert(currentUrl).isEqualTo(url)
        }
    }

    @Test
    fun testCanFindElement() {
        drive {
            to(url)
            element("#header").should(have.text("Kirk"))
            element("#input").should(be.visible)
        }
    }

    @Test
    fun testCanSetValue() {
        drive {
            to(url)
            element("#input").setValue("This is test")
        }
    }

    @Test
    fun shouldIterateOverCollection() {
        drive {
            to(url)
            for (el in all("li")) {
                print(el.text)
            }
        }
    }
}