package com.automation.remarks.kirk.locators

/**
 * Created by sepi on 6/27/2017.
 */
interface ElementLocator<out T> {

    fun find(): T

    val description: String
}