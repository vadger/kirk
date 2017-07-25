package com.automation.remarks.kirk.test.example

import com.automation.remarks.kirk.Kirk.Companion.drive
import com.automation.remarks.kirk.ext.select
import com.automation.remarks.kirk.test.BaseTest
import me.tatarka.assertk.assert
import me.tatarka.assertk.assertions.isEqualTo
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
            assert(select.allSelectedOptions.map { it.text }).isEqualTo(listOf("Alt folk"))
        }
    }

    @Test
    fun testCanSelectOptionByIndex() {
        drive {
            to(secondPage)
            val select = select(".genres")
            select.selectOption(3)
            assert(select.allSelectedOptions.map { it.text }).isEqualTo(listOf("Electroclash"))
        }
    }

    @Test
    fun testSelectIsMultiple() {
        drive {
            to(secondPage)
            assert(select(".genres").isMultiple)
        }
    }

    @Test
    fun testCanGetAllOption() {
        drive {
            to(secondPage)
            val options = select(".genres").options
            assert(options.map { it.text }).isEqualTo(listOf("Alt folk", "Chiptunes", "Electroclash", "G-Funk", "Hair metal"))
        }
    }
}