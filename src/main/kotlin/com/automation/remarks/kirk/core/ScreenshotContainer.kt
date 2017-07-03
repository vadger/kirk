package com.automation.remarks.kirk.core

import org.apache.commons.io.FileUtils
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import java.io.File
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by sergey on 03.07.17.
 */
class ScreenshotContainer(private val driver: WebDriver,
                          private val screenShotPath: String = "${System.getProperty("user.dir")}/build/reports/screen_${System.currentTimeMillis()}.png") {

    companion object {
        private var screenshots: MutableMap<Long, File> = ConcurrentHashMap(4)

        fun getLatestScreenshot(): File? {
            return screenshots[Thread.currentThread().id]
        }
    }

    fun takeScreenshotAsFile(): File? {
        val scrFile = (driver as TakesScreenshot).getScreenshotAs(OutputType.FILE)
        val screenshot = File(screenShotPath)
        FileUtils.copyFile(scrFile, screenshot)
        screenshots.put(Thread.currentThread().id, screenshot)
        return screenshot
    }
}
