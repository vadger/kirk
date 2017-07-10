# Kirk - pragmatic UI test automation

[![Build Status](https://travis-ci.org/SergeyPirogov/kirk.svg?branch=master)](https://travis-ci.org/SergeyPirogov/kirk) [![codecov](https://codecov.io/gh/SergeyPirogov/kirk/branch/master/graph/badge.svg)](https://codecov.io/gh/SergeyPirogov/kirk)

- No Page Factory
- No @FindBy
- Pragmatic DSL
- Informative error messages

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

[DOCUMENTATION](http://automation-remarks.com/kirk/)
