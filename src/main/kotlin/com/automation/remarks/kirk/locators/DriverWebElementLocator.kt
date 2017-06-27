package com.automation.remarks.kirk.locators

import com.automation.remarks.kirk.KElement
import org.openqa.selenium.By
import org.openqa.selenium.SearchContext

/**
 * Created by sepi on 6/27/2017.
 */
class DriverWebElementLocator(private val by: By, private val searchContext: SearchContext) : ElementLocator<KElement> {
    override fun find(): KElement {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val description: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
}