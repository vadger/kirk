# Kirk - pragmatic UI test automation

[![Build Status](https://travis-ci.org/SergeyPirogov/kirk.svg?branch=master)](https://travis-ci.org/SergeyPirogov/kirk)[![codecov](https://codecov.io/gh/SergeyPirogov/kirk/branch/master/graph/badge.svg)](https://codecov.io/gh/SergeyPirogov/kirk)

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
class LoginPage : Page() {

    override val url: String?
        get() = "http://localhost:8086"

    val loginInput = element("#inputEmail3")
    val passwordInput = element("#inputPassword3")

    fun login(name: String, password: String) {
        loginInput.setVal(name)
        passwordInput.setVal(password)
    }
}


@Test fun testCanLoginPageObject2() {
        Browser.drive {
            to(::LoginPage)
                    .login("admin", "admin")
   }
}
```
