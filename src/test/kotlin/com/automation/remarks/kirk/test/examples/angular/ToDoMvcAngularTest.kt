package com.automation.remarks.kirk.test.examples.angular

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.conditions.have
import org.testng.annotations.Test

/**
 * Created by sergey on 01.07.17.
 */
class ToDoMvcAngularTest {

    @Test fun testCanAddNewTaskAndDelete() {
        Browser.drive {
            to(::TodoPage) {
                addTasks("Item0")
                taskList.should(have.size(1))
                deleteTask("Item0")
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