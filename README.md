# Kirk - pragmatic UI test automation

[![Build Status](https://travis-ci.org/SergeyPirogov/kirk.svg?branch=master)](https://travis-ci.org/SergeyPirogov/kirk) [![codecov](https://codecov.io/gh/SergeyPirogov/kirk/branch/master/graph/badge.svg)](https://codecov.io/gh/SergeyPirogov/kirk)

- No Page Factory
- No @FindBy
- Pragmatic DSL

Simple script example:

```java
 @Test fun testCanLogin() {
        Browser.drive {
            to("http://localhost:8086")
            element("#inputEmail3").setVal("admin")
            element("#inputPassword3").setVal("admin")
            element("#parent > form > div:nth-child(3) > div > button").click()
            element("a.navbar-brand").should(have.text("Video service"))
        }
    }
```

Page Object Example:

```java
class TodoPage : Page() {
    override val url: String?
        get() = "http://todomvc.com/examples/angularjs/"

    val taskList = all("label.ng-binding")
    val counter = element("#todo-count strong")

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
```
