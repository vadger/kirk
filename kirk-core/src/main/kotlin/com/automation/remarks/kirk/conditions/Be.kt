package com.automation.remarks.kirk.conditions

import org.openqa.selenium.WebElement

/**
 * Created by sergey on 25.06.17.
 */
class Be {

    val visible = Visibility()

    val invisible = Not(Visibility())
    val empty = CollectionSize(0)
}

@JvmField
val be = Be()