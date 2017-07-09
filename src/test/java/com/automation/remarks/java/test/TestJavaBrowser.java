package com.automation.remarks.java.test;

import com.automation.remarks.kirk.Browser;
import com.automation.remarks.kirk.conditions.Have;
import com.automation.remarks.kirk.test.BaseTest;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by sergey on 09.07.17.
 */
public class TestJavaBrowser extends BaseTest {

  @BeforeMethod
  public void setUp() throws Exception {
    ChromeDriverManager.getInstance().setup();
  }

  @Test
  public void testName() {
    Browser chrome = new Browser(new ChromeDriver());
    chrome.open(getUrl());
    chrome.element("#header").should(new Have().text("Kirk"));
    chrome.quit();
  }
}
