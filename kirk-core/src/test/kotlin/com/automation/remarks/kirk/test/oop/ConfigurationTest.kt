package com.automation.remarks.kirk.test.oop

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.Configuration
import com.automation.remarks.kirk.conditions.have
import com.automation.remarks.kirk.test.BaseTest
import io.github.bonigarcia.wdm.ChromeDriverManager
import me.tatarka.assertk.assert
import me.tatarka.assertk.assertions.isEqualTo
import org.aeonbits.owner.Config.*
import org.aeonbits.owner.ConfigFactory
import org.testng.annotations.Test

/**
 * Created by sergey on 09.07.17.
 */
class ConfigurationTest : BaseTest() {

    @Test
    fun testCanSetCustomConfig() {
        val config: Configuration = ConfigFactory.create(Cust::class.java, System.getProperties())
        assert(config.timeout()).isEqualTo(2000)
    }

    @Test fun testCanSetCustomBrowseConfig() {
        ChromeDriverManager.getInstance().setup()
        val browser = Browser().with {
            baseUrl = url
            holdOpen = false
        }

        browser.open("/")
        browser.all("li").should(have.size(10))
    }
}

@Sources("classpath:browser.properties")
interface Cust : Configuration {
    @DefaultValue("2000")
    @Key("firefox.timeout")
    override fun timeout(): Int

    @Key("holdOpen")
    @DefaultValue("true")
    override fun holdOpen(): Boolean
}