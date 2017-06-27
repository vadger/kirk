import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by sepi on 6/27/2017.
 */
public class SelenideTest extends BaseTest {

    @Test
    public void testName() throws Exception {
        Configuration.browser = "chrome";
        Selenide.open(getUrl());
        $("#asdasdinput").setValue("This is test");
    }
}
