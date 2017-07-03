package com.automation.remarks.kirk.test.examples.react

import com.automation.remarks.kirk.Page
import com.automation.remarks.kirk.core.sleep

/**
 * Created by sergey on 01.07.17.
 */
class TodoPage : Page() {
    override val url: String?
        get() = "http://todomvc.com/examples/react/"

    val input = element(".new-todo")
    val taskList = all(".todo-list li label")

    fun addTask(vararg tasks: String) {
        sleep(1000)
        for (task in tasks) {
            input.setVal(task).pressEnter()
        }
    }
}