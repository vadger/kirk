package com.automation.remarks.kirk.test.vanila

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.core.drive
import com.automation.remarks.kirk.test.BaseTest
import org.testng.annotations.Test

/**
 * Created by sepi on 7/11/2017.
 */
class KElementTest : BaseTest(){

    @Test fun testCanCombine(){
        Browser.drive {
            to(url)
            element(".paginator")
        }
    }

}