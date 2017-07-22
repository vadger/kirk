package com.automation.remarks.java.test;

import com.automation.remarks.kirk.Browser;
import com.automation.remarks.kirk.KElement;
import com.automation.remarks.kirk.Page;
import com.automation.remarks.kirk.conditions.Have;
import com.automation.remarks.kirk.core.Select;
import com.automation.remarks.kirk.test.BaseTest;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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

  @Test
  public void testCalculator() {
    Browser.Companion.open(CalculatorPage::new)
        .calculate("10", "/", "2")
        .result.should(have.text("5"));
  }
}

class CalculatorPage extends Page {
  public CalculatorPage(@NotNull Browser browser) {
    super(browser);
  }

  @Nullable
  @Override
  public String getUrl() {
    return "http://juliemr.github.io/protractor-demo/";
  }

  public KElement first = element("input[ng-model='first']");
  public KElement second = element("input[ng-model='second']");
  public KElement goBtn = element("#gobutton");
  public KElement result = element("h2.ng-binding");
  public Select select = select("select[ng-model='operator']");

  public CalculatorPage calculate(String first, String operation, String second) {
    this.first.setValue(first);
    this.second.setValue(second);
    this.select.selectByVisibleText(operation);
    goBtn.click();
    return this;
  }
}

