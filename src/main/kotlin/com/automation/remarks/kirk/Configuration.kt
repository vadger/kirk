package com.automation.remarks.kirk

import org.aeonbits.owner.Config
import org.aeonbits.owner.Config.DefaultValue
import org.aeonbits.owner.Config.Sources

/**
 * Created by sergey on 25.06.17.
 */
@Sources("classpath:browserHandler.config")
interface Configuration : Config {

    @DefaultValue("4000")
    fun timeout(): Int

    @DefaultValue("0.1")
    fun poolingInterval(): Double

    @DefaultValue("true")
    fun startMaximized(): Boolean

    @DefaultValue("true")
    fun autoClose(): Boolean

    @DefaultValue("1920,1080")
    fun screenSize(): List<Int>

    fun baseUrl(): String?
}