package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PositiveTests {

    WebDriver driver;

    @BeforeTest
    public void prepare() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void loginTest() {

        // ---------------------------------------------------------------------------------------------
        // Execution
        // ---------------------------------------------------------------------------------------------

        log("Starting login test");

        // Maximize browser window
        driver.manage().window().maximize();

        // Open test page
        String url = "https://the-internet.herokuapp.com/login";
        driver.get(url);
        log("Open test page");
        sleep(1000);

        // Enter username
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tomsmith");

        // Enter password
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("SuperSecretPassword!");

        // Click login button
        WebElement loginButton = driver.findElement(By.tagName("button"));
        loginButton.click();
        sleep(1000);

        // ---------------------------------------------------------------------------------------------
        // Verification
        // ---------------------------------------------------------------------------------------------

        // Check expected url
        String expectedUrl = "https://the-internet.herokuapp.com/secure";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not the same expected");

        // logout button is displayed
        WebElement logoutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
        Assert.assertTrue(logoutButton.isDisplayed(), "Logout button is not displayed");

        // successful login message
        WebElement successMessage = driver.findElement(By.xpath("//div[@id='flash']"));
        String expectedMsg = "You logged into a secure area!";
        String actualMsg = successMessage.getText();
        Assert.assertTrue(actualMsg.contains(expectedMsg),
                "Expected message is not the same as expected\nActual Message: " + actualMsg + "\nExpected Message: "
                        + expectedMsg);

    }

    @AfterTest
    public void close() {
        driver.quit();
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void log(String msg) {
        System.out.println("[LOG] ------------------------------------------------------------------------");
        System.out.println("[LOG] " + msg);
        System.out.println("[LOG] ------------------------------------------------------------------------");
    }

}
