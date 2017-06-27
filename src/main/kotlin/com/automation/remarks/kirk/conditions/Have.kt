package com.automation.remarks.kirk.conditions

/**
 * Created by sergey on 25.06.17.
 */

class Have {

    fun text(text: String): Text {
        return Text(text)
    }
}

val have = Have()