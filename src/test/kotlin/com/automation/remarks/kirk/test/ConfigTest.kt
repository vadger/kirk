package com.automation.remarks.kirk.test

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.conditions.have
import com.automation.remarks.kirk.core.sleep
import me.tatarka.assertk.assert
import me.tatarka.assertk.assertAll
import me.tatarka.assertk.assertions.isEqualTo
import org.testng.annotations.AfterMethod
import org.testng.annotations.Test
import java.io.File

/**
 * Created by sergey on 27.06.17.
 */
class ConfigTest : BaseTest() {

    @AfterMethod
    fun tearDown() {
        System.clearProperty("browserName")
        System.clearProperty("timeout")
        System.clearProperty("startMaximized")
        System.clearProperty("baseUrl")
        System.clearProperty("screenSize")
    }

    @Test
    fun testCheckDefaultConfigLoaded() {
        val cfg = Browser.getConfig()
        assertAll {
            assert(cfg.browserName()).isEqualTo("chrome")
            assert(cfg.timeout()).isEqualTo(4000)
            assert(cfg.startMaximized())
        }
    }

    @Test
    fun testCanOverrideDefaultConfig() {
        System.setProperty("browserName", "firefox")
        System.setProperty("timeout", "6000")
        System.setProperty("startMaximized", "false")
        val cfg = Browser.getConfig()
        assertAll {
            assert(cfg.browserName()).isEqualTo("firefox")
            assert(cfg.timeout()).isEqualTo(6000)
            assert(cfg.startMaximized()).isEqualTo(false)
        }
    }

    @Test(enabled = false)
    fun testCanUserConfigFile() {
        val file = File("src/main/resources/browser.config")
        file.writeText("browserName=firefox\ntimeout=300")
        val cfg = Browser.getConfig()
        file.delete()
        assertAll {
            assert(cfg.browserName()).isEqualTo("firefox")
            assert(cfg.timeout()).isEqualTo(300)
            assert(cfg.startMaximized()).isEqualTo(true)
        }
    }

    @Test fun testBrowserCanOpenCanonicalUrl() {
        System.setProperty("baseUrl", url.removeSuffix("/"))
        Browser.drive {
            to("")
            element("#header").should(have.text("Kirk"))
        }
    }

    @Test fun testBrowserWindowSize() {
        System.setProperty("startMaximized", "false")
        System.setProperty("screenSize", "640,480")
        Browser.drive {
            to(url)
            element("#header").should(have.text("Kirk"))
            val size = driver.manage().window().size
            assert(listOf(size.width,size.height)).isEqualTo(listOf(640, 480))
        }
    }

    @Test fun testBrowserCanOpenCanonicalUrlPlacedInConfigFile() {
        val file = File("src/main/resources/browser.config")
        file.writeText("baseUrl=${url}")
        sleep(3000)
        Browser.drive {
            to("")
            element("#header").should(have.text("Kirk"))
        }
        file.delete()
    }
}