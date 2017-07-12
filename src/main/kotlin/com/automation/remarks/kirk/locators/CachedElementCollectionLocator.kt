package com.automation.remarks.kirk.locators

import com.automation.remarks.kirk.KElement

/**
 * Created by sepi on 12.07.17.
 */
class CachedElementCollectionLocator(private val list: List<KElement>) : ElementLocator<List<KElement>> {
    override fun find(): List<KElement> {
        return list
    }

    override val description: String
        get() = "elements collection ${list.map { it.toString() }}"
}