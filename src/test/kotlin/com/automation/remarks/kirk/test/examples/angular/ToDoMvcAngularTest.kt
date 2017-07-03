package com.automation.remarks.kirk.test.examples.angular

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.conditions.have
import io.github.bonigarcia.wdm.ChromeDriverManager
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

/**
 * Created by sergey on 01.07.17.
 */
class ToDoMvcAngularTest {

    @BeforeClass
    fun setUp() {
        ChromeDriverManager.getInstance().setup()
    }

    @Test fun testCanAddNewTaskAndDelete() {
        Browser.drive {
            to(::TodoPage) {
                addTasks("Item")
                taskList.should(have.exactText("Item"))
                deleteTask("Item")
                taskList.should(have.size(0))
            }
        }
    }

    @Test fun testCanDeactivateTask() {
        Browser.drive {
            to(::TodoPage) {
                addTasks("A", "B", "C")
                deactivateTask("A")
                counter.should(have.text("2"))
                goToCompletedTab()
                taskList.should(have.exactText("A"))
            }
        }
    }
}