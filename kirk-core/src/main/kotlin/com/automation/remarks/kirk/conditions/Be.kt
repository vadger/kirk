@file:JvmName("Be")
package com.automation.remarks.kirk.conditions


/**
 * Created by sergey on 25.06.17.
 */
@JvmField val visible = Visibility()

@JvmField val invisible = Not(Visibility())
@JvmField val empty = CollectionSize(0)

