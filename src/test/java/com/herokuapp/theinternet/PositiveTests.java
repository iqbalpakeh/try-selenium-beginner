package com.herokuapp.theinternet;

import com.herokuapp.theinternet.util.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PositiveTests {

    WebDriver webDriver;

    @BeforeTest
    public void prepare() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        webDriver = new ChromeDriver();
    }

    @Test
    public void loginTest() {

        // ---------------------------------------------------------------------------------------------
        // Execution
        // ---------------------------------------------------------------------------------------------

        Util.log("Starting login test");

        // Maximize browser window
        webDriver.manage().window().maximize();

        // Open test page
        String url = "https://the-internet.herokuapp.com/login";
        webDriver.get(url);
        Util.log("Open test page");
        Util.sleep(1000);

        // Enter username
        WebElement username = webDriver.findElement(By.id("username"));
        username.sendKeys("tomsmith");

        // Enter password
        WebElement password = webDriver.findElement(By.name("password"));
        password.sendKeys("SuperSecretPassword!");

        // Click login button
        WebElement loginButton = webDriver.findElement(By.tagName("button"));
        loginButton.click();
        Util.sleep(1000);

        // ---------------------------------------------------------------------------------------------
        // Verification
        // ---------------------------------------------------------------------------------------------

        // Check expected url
        String expectedUrl = "https://the-internet.herokuapp.com/secure";
        String actualUrl = webDriver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not the same expected");

        // logout button is displayed
        WebElement logoutButton = webDriver.findElement(By.xpath("//a[@class='button secondary radius']"));
        Assert.assertTrue(logoutButton.isDisplayed(), "Logout button is not displayed");

        // successful login message
        WebElement successMessage = webDriver.findElement(By.xpath("//div[@id='flash']"));
        String expectedMsg = "You logged into a secure area!";
        String actualMsg = successMessage.getText();
        Assert.assertTrue(actualMsg.contains(expectedMsg),
                "Expected message is not the same as expected\nActual Message: " + actualMsg + "\nExpected Message: "
                        + expectedMsg);

    }

    @AfterTest
    public void close() {
        webDriver.quit();
    }

}
