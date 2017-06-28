package com.automation.remarks.kirk.conditions

/**
 * Created by sergey on 25.06.17.
 */

class Have {

    fun text(text: String): Text {
        return Text(text)
    }

    fun collectionSize(size: Int): CollectionSize {
        return CollectionSize(size)
    }

    fun exactText(vararg text: String): CollectionExactText {
        return CollectionExactText(text)
    }
}

val have = Have()