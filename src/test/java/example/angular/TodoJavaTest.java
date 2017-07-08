package example.angular;

import com.automation.remarks.kirk.BrowserHandler;
import com.automation.remarks.kirk.conditions.Have;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import kotlin.Unit;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by sergey on 08.07.17.
 */
public class TodoJavaTest {

  private static Have have = new Have();

  @BeforeMethod
  public void setUp() {
    ChromeDriverManager.getInstance().setup();
  }

  @Test
  public void testCanAddNewTaskAndDelete() {
    BrowserHandler chrome = new BrowserHandler(new ChromeDriver());

    chrome.to(TodoPage::new, todoPage -> {
      todoPage.addTasks("A", "B", "C");
      todoPage.taskList.should(have.size(3));
      todoPage.deleteTask("A");
      todoPage.taskList.should(have.size(2));
      return Unit.INSTANCE;
    });
  }
}
