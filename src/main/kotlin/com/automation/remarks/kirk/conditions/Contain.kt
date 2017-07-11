package com.automation.remarks.kirk.conditions

/**
 * Created by sepi on 7/11/2017.
 */
class Contain {

    fun elementWithText(text: String): CollectionContainText {
        return CollectionContainText(text)
    }
}

val contain = Contain()