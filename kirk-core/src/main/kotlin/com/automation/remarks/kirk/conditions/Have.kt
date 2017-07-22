package com.automation.remarks.kirk.conditions

/**
 * Created by sergey on 25.06.17.
 */

class Have {

    fun text(text: String): Text {
        return Text(text)
    }

    fun size(size: Int): CollectionSize {
        return CollectionSize(size)
    }

    fun exactText(vararg text: String): CollectionExactText {
        return CollectionExactText(text)
    }

    fun attr(name: String, value: String): AttributeValue {
        return AttributeValue(name, value)
    }

    fun cssClass(cssClass: String): ElementCondition {
        return CssClassValue(cssClass)
    }
}

@JvmField
val have = Have()