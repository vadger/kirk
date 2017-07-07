import com.automation.remarks.kirk.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by sergey on 07.07.17.
 */
public class Demo {

  public <T extends Page> T to(Class<T> tClass) {
    try {
      Constructor<T> declaredConstructor = tClass.getDeclaredConstructor(WebDriver.class);
      return declaredConstructor.newInstance(new ChromeDriver());
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }
    return null;
  }

}
