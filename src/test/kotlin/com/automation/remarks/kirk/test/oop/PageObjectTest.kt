package com.automation.remarks.kirk.test.oop

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.conditions.have
import com.automation.remarks.kirk.test.BaseTest
import com.automation.remarks.kirk.test.pages.StartPage
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

/**
 * Created by sergey on 09.07.17.
 */
class PageObjectTest : BaseTest() {

    lateinit var chrome: Browser

    @BeforeMethod
    fun setUp() {
        chrome = Browser()
        chrome.open(url)
    }

    @AfterMethod
    fun tearDown() {
        chrome.quit()
    }

    @Test
    fun testCanOpenStartPage() {
        val startPage = chrome.to(::StartPage)
        startPage.header.should(have.text("Kirk"))
    }

    @Test
    fun testCanOpenStartPageAndUseClosure() {
        chrome.at(::StartPage) {
            list.should(have.size(3))
        }
    }
}