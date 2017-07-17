package com.automation.remarks.java.test;

import com.automation.remarks.kirk.Browser;
import com.automation.remarks.kirk.conditions.Have;
import com.automation.remarks.kirk.test.BaseTest;
import org.testng.annotations.Test;

/**
 * Created by sergey on 09.07.17.
 */
public class TestJavaBrowser extends BaseTest {

  private Have have = new Have();

  @Test
  public void testName() {
    Browser chrome = new Browser();
    chrome.open(getUrl());
    chrome.element("#header").should(have.text("Kirk"));
  }
}
