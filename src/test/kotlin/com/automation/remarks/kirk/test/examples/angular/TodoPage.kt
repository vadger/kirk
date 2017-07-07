package com.automation.remarks.kirk.test.examples.angular

import com.automation.remarks.kirk.Page
import com.automation.remarks.kirk.core.byXpath
import com.automation.remarks.kirk.core.sleep

/**
 * Created by sergey on 01.07.17.
 */
class TodoPage : Page() {
    override val url: String?
        get() = "http://todomvc.com/examples/angularjs/"

    val counter by lazy { element("#todo-count strong") }
    val taskList by lazy { all("label.ng-binding") }

    fun addTasks(vararg tasks: String) {
        sleep(1000)
        for (task in tasks) {
            element("#new-todo")
                    .setVal(task)
                    .pressEnter()
        }
    }

    fun deleteTask(name: String) {
        element("#todo-list li div input").hover()
        element(byXpath("//label[text()='$name']/following-sibling::button"))
                .click()
    }

    fun deactivateTask(vararg tasks: String) {
        for (task in tasks) {
            element(byXpath("//label[text()='$task']/preceding-sibling::input")).click()
        }
    }

    fun goToCompletedTab() {
        element("#filters li:nth-child(3) a").click()
    }
}