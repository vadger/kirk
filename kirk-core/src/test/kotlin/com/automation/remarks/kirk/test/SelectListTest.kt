package com.automation.remarks.kirk.test

import com.automation.remarks.kirk.Kirk.Companion.drive
import com.automation.remarks.kirk.conditions.BaseCondition
import com.automation.remarks.kirk.conditions.ConditionAssert
import com.automation.remarks.kirk.conditions.Description
import com.automation.remarks.kirk.core.Select
import com.automation.remarks.kirk.ext.select
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
            assert(select.allSelectedOptions.map { it.text }).isEqualTo(listOf("G-Funk"))
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

abstract class SelectCondition : BaseCondition<Select>()

class SelectedOptions(val options: List<String>) : SelectCondition() {
    override fun matches(item: Select): Boolean {
        return item.allSelectedOptions.map { it.text } == options
    }

    override fun description(item: Select): Description {
        return Description(item.allSelectedOptions.map { it.text }, options).apply {
            message = """
            failed to assert ${this@SelectedOptions}
            for $item
            reason: $message
                    """
        }
    }
}

fun options(vararg options: String): SelectedOptions {
    return SelectedOptions(options.toList())
}

fun Select.should(condition: SelectCondition) {
    ConditionAssert.evaluate(this, condition)
}