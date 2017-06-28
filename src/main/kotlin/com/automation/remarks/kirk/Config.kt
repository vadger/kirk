package com.automation.remarks.kirk

/**
 * Created by sergey on 27.06.17.
 */
class Config(val startMaximized: Boolean = false,
             val autoClose: Boolean = true,
             val screenSize: List<Int> = listOf(1920, 1080),
             val baseUrl: String = "") : BrowserConfig {
    override fun baseUrl(): String {
        return baseUrl
    }

    override fun screenSize(): List<Int> {
        return screenSize
    }

    override fun browserName(): String {
        TODO("not implemented")
    }

    override fun timeout(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun poolingInterval(): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun startMaximized(): Boolean {
        return startMaximized
    }

    override fun autoClose(): Boolean {
        return autoClose
    }
}