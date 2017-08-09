package com.automation.remarks.kirk.test

import com.automation.remarks.kirk.core.configuration
import me.tatarka.assertk.assertions.isEqualTo
import org.testng.annotations.Test

/**
 * Created by sergey on 09.08.17.
 */
class DesiredCapsTest {

    @Test
    fun testName() {
        System.setProperty("kirk.desired.capabilities", "browser=chrome, version=59.0")
        val capabilities = configuration.capabilities()

        val map = HashMap<String, Any>()
        capabilities
                .map { it.split("=") }
                .forEach { map[it[0]] = it[1] }

        me.tatarka.assertk.assert(map["browser"]).isEqualTo("chrome")
        me.tatarka.assertk.assert(map["version"]).isEqualTo("59.0")
    }
}