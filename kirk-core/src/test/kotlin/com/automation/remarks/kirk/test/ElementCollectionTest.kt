package com.automation.remarks.kirk.test

import com.automation.remarks.kirk.Kirk.Companion.drive
import com.automation.remarks.kirk.conditions.have
import com.automation.remarks.kirk.test.BaseTest
import org.testng.annotations.Test

/**
 * Created by sergey on 10.07.17.
 */
class ElementCollectionTest : BaseTest() {

    @Test fun testCanSelectSingleElementFromCollection() {
        drive {
            to(url)
            all("li")[1].should(have.text("Два"))
        }
    }

    @Test fun testCanSelectElementByCSS3() {
        drive {
            to(url)
            element(":link").should(have.text("To 2 page"))
        }
    }

    @Test fun testCanCompareExactListText() {
        drive {
            to(url)
            all("li").should(have.exactText("Один", "Два", "Три", "1", "2", "2.1", "2.2", "3", "3.1", "3.2"))
        }
    }

    @Test fun testCanCheckCollectionContainElementText() {
        drive {
            to(url)
            all("li").should(have.elementWithText("Три"))
        }
    }
}