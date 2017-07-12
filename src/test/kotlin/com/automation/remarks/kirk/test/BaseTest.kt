package com.automation.remarks.kirk.test

import com.automation.remarks.kirk.test.helpers.JettyServer
import io.github.bonigarcia.wdm.ChromeDriverManager
import io.github.bonigarcia.wdm.FirefoxDriverManager
import me.tatarka.assertk.assertions.hasClass
import org.openqa.selenium.net.PortProber
import org.testng.annotations.AfterSuite
import org.testng.annotations.BeforeSuite
import kotlin.reflect.KClass

/**
 * Created by sepi on 6/27/2017.
 */
abstract class BaseTest {

    val jetty = JettyServer(PortProber.findFreePort())
    val url: String
        get() = jetty.server.uri.toASCIIString()


    @BeforeSuite
    fun runServer() {
        ChromeDriverManager.getInstance().setup()
        FirefoxDriverManager.getInstance().setup()
        jetty.runServer()
        FirefoxDriverManager.getInstance().setup()
    }

    @AfterSuite
    fun tearDown() {
        jetty.stop()
    }

    fun <T : Any> assertExceptionThrown(kclass: KClass<out T>, closure: () -> Unit) {
        me.tatarka.assertk.assert {
            closure()
        }.throwsError {
            it.hasClass(kclass)
        }
    }
}