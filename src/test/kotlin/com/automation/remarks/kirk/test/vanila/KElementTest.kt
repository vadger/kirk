package com.automation.remarks.kirk.test.vanila

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.KElement
import com.automation.remarks.kirk.conditions.have
import com.automation.remarks.kirk.core.*
import com.automation.remarks.kirk.test.BaseTest
import org.testng.annotations.Test

/**
 * Created by sepi on 7/11/2017.
 */
class KElementTest : BaseTest() {

    @Test fun testCanFindFirstChild() {
        // tag::child[]
        Browser.drive {
            to(url)
            s("ul").firstChild().should(have.text("Один"))
            s("ul").lastChild().should(have.text("Три"))
        }
        // end::child[]
    }

    @Test fun testCanFindLastChild() {
        Browser.drive {
            to(url)
            s("ul").lastChild().should(have.text("Три"))
        }
    }

    @Test fun testCanFindFirstParent() {
        // tag::parent[]
        Browser.drive {
            to(url)
            s("div.b").parent().should(have.cssClass("a"))
        }
        // end::parent[]
    }

    @Test fun testCanFindChildren() {
        Browser.drive {
            to(url)
            element("ul#with_children").children("li").should(have.exactText("1", "2", "2.1", "2.2", "3", "3.1", "3.2"))
        }
    }

    @Test fun testCanUploadFile() {
        // tag::uploadFile[]
        Browser.drive {
            element("input").uploadFile("")
        }
        // end::uploadFile[]
    }

    @Test fun testCanCompose() {
        val browser = Browser().apply { to(url) }
        // tag::composition[]
        browser.element("ul.list").all("li").should(have.size(3))
        // end::composition[]
        browser.quit()
    }
}

fun Browser.s(cssLocator: String): KElement {
    return element(cssLocator)
}