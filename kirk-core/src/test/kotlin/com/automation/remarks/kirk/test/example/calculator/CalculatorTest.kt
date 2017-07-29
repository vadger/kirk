package com.automation.remarks.kirk.test.example.calculator

import com.automation.remarks.kirk.Kirk.Companion.drive
import com.automation.remarks.kirk.Kirk.Companion.open
import com.automation.remarks.kirk.conditions.text
import com.automation.remarks.kirk.ext.select
import org.testng.annotations.Test

/**
 * Created by sergey on 17.07.17.
 */
class CalculatorTest {

    @Test fun testCanAddTwoNumbers() {
        drive {
            to("http://juliemr.github.io/protractor-demo/")
            element("input[ng-model='first']").setValue("1")
            element("input[ng-model='second']").setValue("2")
            select("select[ng-model='operator']").selectOption("+")
            element("#gobutton").click()
            element("h2.ng-binding").shouldHave(text("3"))
        }
    }

    @Test fun testCanDivide() {
        open(::Calculator) {
            first.setValue("10")
            second.setValue("2")
            select.selectOption("/")
            goBtn.click()
            result.shouldHave(text("5"))
        }
    }
}