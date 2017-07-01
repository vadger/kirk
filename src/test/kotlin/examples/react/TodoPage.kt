package examples.react

import com.automation.remarks.kirk.KElementCollection
import com.automation.remarks.kirk.Page
import com.automation.remarks.kirk.sleep

/**
 * Created by sergey on 01.07.17.
 */
class TodoPage : Page() {
    override val url: String?
        get() = "http://todomvc.com/examples/react/"

    val input = element(".new-todo")

    fun addTask(vararg tasks: String) {
        sleep(1000)
        for (task in tasks) {
            input.setVal(task).pressEnter()
        }
    }

    fun taskList(): KElementCollection {
        return all(".todo-list li label")
    }
}