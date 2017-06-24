package com.automation.remarks.kirk

import org.openqa.selenium.WebDriver

/**
 * Created by sergey on 24.06.17.
 */
internal class BrowserImpl(val driver: WebDriver) : Browser, WebDriver by driver {

}