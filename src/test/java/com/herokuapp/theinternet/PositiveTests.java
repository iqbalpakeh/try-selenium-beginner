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

public class PositiveTests {

    private WebDriver mDriver;

    @BeforeTest
    public void prepare() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
        mDriver = new FirefoxDriver();
    }

    @Test
    public void loginTest() {

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
        password.sendKeys("SuperSecretPassword!");

        // Click login button
        WebElement loginButton = mDriver.findElement(By.tagName("button"));
        loginButton.click();
        Util.sleep(1000);

        // ---------------------------------------------------------------------------------------------
        // Verification
        // ---------------------------------------------------------------------------------------------

        // Check expected url
        String expectedUrl = "https://the-internet.herokuapp.com/secure";
        String actualUrl = mDriver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not the same expected");

        // logout button is displayed
        WebElement logoutButton = mDriver.findElement(By.xpath("//a[@class='button secondary radius']"));
        Assert.assertTrue(logoutButton.isDisplayed(), "Logout button is not displayed");

        // successful login message
        WebElement successMessage = mDriver.findElement(By.xpath("//div[@id='flash']"));
        String expectedMsg = "You logged into a secure area!";
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
