package example.angular;

import com.automation.remarks.kirk.Browser;
import com.automation.remarks.kirk.KElement;
import com.automation.remarks.kirk.KElementCollection;
import com.automation.remarks.kirk.Page;
import org.jetbrains.annotations.Nullable;

import static com.automation.remarks.kirk.core.SelectorsKt.byXpath;
import static com.automation.remarks.kirk.core.WaiterKt.sleep;

/**
 * Created by sergey on 08.07.17.
 */
public class TodoPage extends Page {
  public TodoPage(Browser browser) {
    super(browser);
  }

  @Nullable
  @Override
  public String getUrl() {
    return "http://todomvc.com/examples/angularjs/";
  }

  public KElement counter = element("#todo-count strong");
  public KElementCollection taskList = all("label.ng-binding");

  public TodoPage addTasks(String... tasks) {
    sleep(1000);
    for (String task : tasks) {
      element("#new-todo")
          .setValue(task)
          .pressEnter();
    }
    return this;
  }

  public TodoPage deleteTask(String name) {
    element("#todo-list li div input").hover();
    element(byXpath("//label[text()='" + name + "']/following-sibling::button"))
        .click();
    return this;
  }

  public TodoPage deactivateTask(String... tasks) {
    for (String task : tasks) {
      element(byXpath("//label[text()='" + task + "']/preceding-sibling::input")).click();
    }
    return this;
  }

  public TodoPage goToCompletedTab() {
    element("#filters li:nth-child(3) a").click();
    return this;
  }
}
