package com.automation.remarks.kirk.test

import com.automation.remarks.kirk.Kirk.Companion.drive
import com.automation.remarks.kirk.conditions.multiple
import com.automation.remarks.kirk.conditions.options
import com.automation.remarks.kirk.conditions.selected
import com.automation.remarks.kirk.ext.select
import org.testng.annotations.Test

/**
 * Created by sergey on 25.07.17.
 */
class SelectListTest : BaseTest() {

    val secondPage = "${url}second_page.html"

    @Test
    fun testCanSelectOptionByText() {
        drive {
            to(secondPage)
            val select = select(".genres")
            select.selectOption("Alt folk")
            select.shouldHave(selected("Alt folk"))
        }
    }

    @Test
    fun testCanSelectOptionByIndex() {
        drive {
            to(secondPage)
            val select = select(".genres")
            select.selectOption(3)
            select.shouldHave(selected("G-Funk"))
        }
    }

    @Test
    fun testSelectIsMultiple() {
        drive {
            to(secondPage)
            select(".genres").shouldBe(multiple)
        }
    }

    @Test
    fun testCanGetAllOption() {
        drive {
            to(secondPage)
            select(".genres").shouldHave(options("Alt folk", "Chiptunes", "Electroclash", "G-Funk", "Hair metal"))
        }
    }
}