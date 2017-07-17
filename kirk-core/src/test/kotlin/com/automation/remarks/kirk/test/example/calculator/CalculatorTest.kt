package com.automation.remarks.kirk.test.example.calculator

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.conditions.have
import com.automation.remarks.kirk.ext.select
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

/**
 * Created by sergey on 17.07.17.
 */
class CalculatorTest {
    @BeforeClass
    fun setUp() {
        System.setProperty("kirk.baseUrl", "http://juliemr.github.io/protractor-demo/")
    }

    @Test fun testCanAddTwoNumbers() {
        Browser.drive {
            to("http://juliemr.github.io/protractor-demo/")
            element("input[ng-model='first']").setValue("1")
            element("input[ng-model='second']").setValue("2")
            select("select[ng-model='operator']").selectByVisibleText("+")
            element("#gobutton").click()
            element("h2.ng-binding").should(have.text("3"))
        }
    }

    @Test fun testCanDivide() {
        Browser.drive {
            to(::Calculator) {
                first.setValue("10")
                second.setValue("2")
                select.selectByVisibleText("/")
                goBtn.click()
                result.should(have.text("5"))
            }
        }
    }
}