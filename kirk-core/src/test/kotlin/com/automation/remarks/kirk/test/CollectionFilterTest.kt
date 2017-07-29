package com.automation.remarks.kirk.test

import com.automation.remarks.kirk.Kirk
import com.automation.remarks.kirk.conditions.have
import org.testng.annotations.Test

/**
 * Created by sergey on 29.07.17.
 */
class CollectionFilterTest : BaseTest() {

    @Test
    fun testCanFilterCollection() {
        Kirk.drive {
            to(url)
            all(".list li").should(have.size(3))
            all(".list li").that(have.cssClass("Один")).should(have.size(2))
        }
    }
}


