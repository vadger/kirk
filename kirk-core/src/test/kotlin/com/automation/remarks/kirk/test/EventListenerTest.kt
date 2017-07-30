package com.automation.remarks.kirk.test

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.EventListener
import com.automation.remarks.kirk.conditions.text
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.testng.annotations.Test

/**
 * Created by sergey on 30.07.17.
 */
class EventListenerTest : BaseTest() {

    @Test
    fun testCanLogEvents() {
        val chrome = Browser(listener = LoggerListener())
        chrome.to(url)
        chrome.element("#header").shouldHave(text("Kirk"))
    }
}

class LoggerListener : EventListener {
    override fun onFail(exception: Exception) = println("Epic Fail")

    override fun beforeElementLocation(by: By, driver: WebDriver) = println("$by")

    override fun afterNavigation(url: String, driver: WebDriver) {}

    override fun onStart() = println("On start")

    override fun beforeNavigation(url: String, driver: WebDriver) = println("Navigation to $url")
}