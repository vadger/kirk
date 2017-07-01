package examples

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.KElementCollection
import com.automation.remarks.kirk.Page
import com.automation.remarks.kirk.conditions.have
import com.automation.remarks.kirk.sleep
import io.github.bonigarcia.wdm.ChromeDriverManager
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

/**
 * Created by sergey on 01.07.17.
 */
class ToDoMvcReactTest {

    @BeforeClass
    fun setUp() {
        ChromeDriverManager.getInstance().setup()
    }

    @Test fun testCanAddNewItemInList() {
        Browser.drive {
            to(::TodoPage) {
                addTask("Item-1", "Item-2")
                taskList().should(have.collectionSize(2))
            }
        }
    }
}

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