package com.automation.remarks.kirk.test

import com.automation.remarks.kirk.test.helpers.JettyServer
import io.github.bonigarcia.wdm.FirefoxDriverManager
import me.tatarka.assertk.assertions.hasClass
import org.testng.annotations.BeforeSuite
import kotlin.reflect.KClass

/**
 * Created by sepi on 6/27/2017.
 */
abstract class BaseTest {

    val url: String = "http://localhost:32943/"

    @BeforeSuite
    fun runServer() {
        JettyServer(32943).runServer()
        FirefoxDriverManager.getInstance().setup()
    }


    fun <T : Any> assertExceptionThrown(kclass: KClass<out T>, closure: () -> Unit) {
        me.tatarka.assertk.assert {
            closure()
        }.throwsError {
            it.hasClass(kclass)
        }
    }
}