package com.automation.remarks.kirk.test.vanila

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.KElement
import com.automation.remarks.kirk.conditions.have
import com.automation.remarks.kirk.core.drive
import com.automation.remarks.kirk.test.BaseTest
import com.automation.remarks.kirk.test.pages.SecondPage
import com.automation.remarks.kirk.test.pages.StartPage
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.interactions.Actions
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
            holdOpen = true
            to(::StartPage) {
                list should(have.size(3))
                link.click()
                at(::SecondPage).header.should(have.text("Second page"))
            }
            interact {
                keyDown(Keys.CONTROL)
                click(element(By.name("genres")).firstChild())
                click(element(By.name("genres")).lastChild())
                keyUp(Keys.CONTROL)
            }
        }
    }

    // tag::testCanDriveScripts[]
    @Test fun testCanDriveScripts(){
        Browser.drive {
            open(url)
            element("#header").should(have.text("Kirk"))
            element(".paginator a").click()
            element("#header").should(have.text("Second page"))
        }
    }
    // end::testCanDriveScripts[]
}

fun Actions.hover(element: KElement){
    this.moveToElement(element.webElement)
}

fun Actions.click(element: KElement){
    this.click(element.webElement)
}