package com.herokuapp.theinternet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NegativeTests {

    WebDriver mDriver;

    @BeforeTest
    public void prepare() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        mDriver = new ChromeDriver();
    }

    @Test
    public void incorrectUserNameTest() {

    }

    @AfterTest
    public void close() {
        mDriver.quit();
    }

}
