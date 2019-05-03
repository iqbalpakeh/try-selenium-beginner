package com.herokuapp.theinternet;

import com.herokuapp.theinternet.util.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NegativeTests {

    private WebDriver mDriver;

    @BeforeTest
    public void prepare() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
        mDriver = new FirefoxDriver();
    }

    @Test(priority = 1)
    public void incorrectUserNameTest() {

        // ---------------------------------------------------------------------------------------------
        // Execution
        // ---------------------------------------------------------------------------------------------

        Util.log("Starting login test");

        // Maximize browser window
        mDriver.manage().window().maximize();

        // Open test page
        String url = "https://the-internet.herokuapp.com/login";
        mDriver.get(url);
        Util.log("Open test page");
        Util.sleep(1000);

        // Enter username
        WebElement username = mDriver.findElement(By.id("username"));
        username.sendKeys("incorrect-username");

        // Enter password
        WebElement password = mDriver.findElement(By.name("password"));
        password.sendKeys("SuperSecretPassword!");

        // Click login button
        WebElement loginButton = mDriver.findElement(By.tagName("button"));
        loginButton.click();
        Util.sleep(1000);

        // ---------------------------------------------------------------------------------------------
        // Verification
        // ---------------------------------------------------------------------------------------------

        // Check expected url
        String expectedUrl = "https://the-internet.herokuapp.com/login";
        String actualUrl = mDriver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not the same expected");

        // Error login message
        WebElement successMessage = mDriver.findElement(By.xpath("//div[@id='flash']"));
        String expectedMsg = "Your username is invalid!";
        String actualMsg = successMessage.getText();
        Assert.assertTrue(actualMsg.contains(expectedMsg),
                "Expected message is not the same as expected\nActual Message: " + actualMsg + "\nExpected Message: "
                        + expectedMsg);

    }

    @Test(priority = 2)
    public void incorrectPasswordTest() {

        // ---------------------------------------------------------------------------------------------
        // Execution
        // ---------------------------------------------------------------------------------------------

        Util.log("Starting login test");

        // Maximize browser window
        mDriver.manage().window().maximize();

        // Open test page
        String url = "https://the-internet.herokuapp.com/login";
        mDriver.get(url);
        Util.log("Open test page");
        Util.sleep(1000);

        // Enter username
        WebElement username = mDriver.findElement(By.id("username"));
        username.sendKeys("tomsmith");

        // Enter password
        WebElement password = mDriver.findElement(By.name("password"));
        password.sendKeys("incorrect-password!");

        // Click login button
        WebElement loginButton = mDriver.findElement(By.tagName("button"));
        loginButton.click();
        Util.sleep(1000);

        // ---------------------------------------------------------------------------------------------
        // Verification
        // ---------------------------------------------------------------------------------------------

        // Check expected url
        String expectedUrl = "https://the-internet.herokuapp.com/login";
        String actualUrl = mDriver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not the same expected");

        // Error login message
        WebElement successMessage = mDriver.findElement(By.xpath("//div[@id='flash']"));
        String expectedMsg = "Your password is invalid!";
        String actualMsg = successMessage.getText();
        Assert.assertTrue(actualMsg.contains(expectedMsg),
                "Expected message is not the same as expected\nActual Message: " + actualMsg + "\nExpected Message: "
                        + expectedMsg);

    }

    @AfterTest
    public void close() {
        mDriver.quit();
    }

}
