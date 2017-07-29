package com.automation.remarks.kirk.test

import com.automation.remarks.kirk.Kirk
import com.automation.remarks.kirk.conditions.cssClass
import com.automation.remarks.kirk.conditions.size
import org.testng.annotations.Test

/**
 * Created by sergey on 29.07.17.
 */
class CollectionFilterTest : BaseTest() {

    @Test
    fun testCanFilterCollection() {
        Kirk.drive {
            to(url)
            all(".list li").shouldHave(size(3))
            all(".list li").filterBy(cssClass("Один")).shouldHave(size(1))
        }
    }
}


