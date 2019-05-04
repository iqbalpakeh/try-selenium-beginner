package com.herokuapp.theinternet;

import com.herokuapp.theinternet.util.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTests {

    private WebDriver mDriver;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    private void setUp(@Optional String browser) {

        // Create driver
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
                mDriver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
                mDriver = new FirefoxDriver();
                break;
            default:
                Util.log("Do not know how to start " + browser + ", starting chrome instead!");
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
                mDriver = new ChromeDriver();
                break;
        }

        // Maximize browser window
        mDriver.manage().window().maximize();
    }

    @Test(priority = 1, groups = {"positiveTests"})
    public void positiveLoginTest() {

        // ---------------------------------------------------------------------------------------------
        // Execution
        // ---------------------------------------------------------------------------------------------

        Util.log("Starting positiveLoginTest");

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

    @Parameters({"username", "password", "expectedMessage"})
    @Test(priority = 2, groups = {"negativeTests"})
    public void negativeLoginTest(String username, String password, String expectedErrorMessage) {

        // ---------------------------------------------------------------------------------------------
        // Execution
        // ---------------------------------------------------------------------------------------------

        Util.log("Starting negativeLoginTest");

        // Maximize browser window
        mDriver.manage().window().maximize();

        // Open test page
        String url = "https://the-internet.herokuapp.com/login";
        mDriver.get(url);
        Util.log("Open test page");
        Util.sleep(1000);

        // Enter username
        WebElement usernameElement = mDriver.findElement(By.id("username"));
        usernameElement.sendKeys(username);

        // Enter password
        WebElement passwordElement = mDriver.findElement(By.name("password"));
        passwordElement.sendKeys(password);

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
        String expectedMsg = expectedErrorMessage;
        String actualMsg = successMessage.getText();
        Assert.assertTrue(actualMsg.contains(expectedMsg),
                "Expected message is not the same as expected\nActual Message: " + actualMsg + "\nExpected Message: "
                        + expectedMsg);

    }

    @AfterMethod(alwaysRun = true)
    private void tearDown() {
        mDriver.quit();
    }

}
