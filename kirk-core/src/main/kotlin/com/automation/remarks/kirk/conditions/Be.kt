package com.automation.remarks.kirk.conditions

/**
 * Created by sergey on 25.06.17.
 */
class Be {

    val visible = ElementVisibility()

    val invisible = Not(ElementVisibility())
}

@JvmField
val be = Be()