package com.automation.remarks.kirk.test.vanila

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.KElement
import com.automation.remarks.kirk.KElementCollection
import com.automation.remarks.kirk.conditions.have
import com.automation.remarks.kirk.core.drive
import com.automation.remarks.kirk.test.BaseTest
import org.openqa.selenium.By
import org.testng.annotations.Test

/**
 * Created by sepi on 7/11/2017.
 */
class KElementTest : BaseTest() {

    @Test fun testCanFindFirstChild() {
        Browser.drive {
            to(url)
            s("ul").firstChild().should(have.text("Один"))
            s("ul").lastChild().should(have.text("Три"))
        }
    }

    @Test fun testCanFindLastChild() {
        Browser.drive {
            to(url)
            s("ul").lastChild().should(have.text("Три"))
        }
    }

    @Test fun testCanFindFirstParent() {
        Browser.drive {
            to(url)
            s("div.b").parent().should(have.cssClass("a"))
        }
    }

    @Test fun testCanFindChildren() {
        Browser.drive {
            to(url)
            element("ul#with_children").children("li").should(have.exactText("1", "2", "2.1", "2.2", "3", "3.1", "3.2"))
        }


    }
}


private fun KElement.children(locator: String = "*"): KElementCollection {
    return this.all(locator)
}

fun Browser.s(cssLocator: String): KElement {
    return element(cssLocator)
}

fun KElement.firstChild(): KElement {
    return this.element(":first-child")
}

fun KElement.lastChild(): KElement {
    return this.element(":last-child")
}

fun KElement.parent(): KElement {
    return this.element(By.xpath(".."))
}