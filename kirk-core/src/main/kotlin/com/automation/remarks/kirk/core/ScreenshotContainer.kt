package com.automation.remarks.kirk.core

import java.io.File
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by sergey on 03.07.17.
 */
val screenshots: MutableMap<Long, File> = ConcurrentHashMap(4)

fun getLatestScreenshot(): File? {
    return screenshots[Thread.currentThread().id]
}

