package com.automation.remarks.kirk.conditions

/**
 * Created by sergey on 25.06.17.
 */
class Be {

    val visible = Visibility()

    val invisible = Not(Visibility())
}

@JvmField
val be = Be()