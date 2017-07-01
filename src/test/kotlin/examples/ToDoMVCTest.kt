package examples

import com.automation.remarks.kirk.Browser
import com.automation.remarks.kirk.byXpath
import com.automation.remarks.kirk.conditions.have
import io.github.bonigarcia.wdm.ChromeDriverManager
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

/**
 * Created by sergey on 01.07.17.
 */
class ToDoMVCTest {

    val url = "http://todomvc.com/examples/angularjs/"

    @BeforeMethod
    fun setUp() {
        ChromeDriverManager.getInstance().setup()
    }

    @Test fun testCanAddNewTaskAndDelete() {
        Browser.drive {
            to(url)
            sleep(1000)
            element("#new-todo")
                    .setVal("Item")
                    .pressEnter()
            all("label.ng-binding").should(have.exactText("Item"))
            element("#todo-list li div input").hover()
            element(byXpath("//label[text()='Item']/following-sibling::button"))
                    .click()
            all("#todo-list li").should(have.collectionSize(0))
        }
    }

    @Test fun testCanDeactivateTask() {
        Browser.drive {
            to(url)
            sleep(1000)
            for (task in listOf("A", "B", "C")) {
                element("#new-todo").setVal(task).pressEnter()
            }
            element(byXpath("//label[text()='A']/preceding-sibling::input")).click()
            element("#todo-count strong").should(have.text("2"))
            element("#filters li:nth-child(3) a").click()
            all("label.ng-binding").should(have.exactText("A"))
        }
    }

    private fun sleep(i: Long) {
        Thread.sleep(i)
    }

}