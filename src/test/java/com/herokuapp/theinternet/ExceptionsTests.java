package com.herokuapp.theinternet;

import com.herokuapp.theinternet.util.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class ExceptionsTests {

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

    @Test(priority = 1)
    public void notVisibleTest() {

        // ---------------------------------------------------------------------------------------------
        // Execution
        // ---------------------------------------------------------------------------------------------

        Util.log("Starting notVisibleTest");

        // Open test page
        String url = "https://the-internet.herokuapp.com/dynamic_loading/1";
        mDriver.get(url);
        Util.log("Open test page");

        // Press Start button
        WebElement startButton = mDriver.findElement(By.xpath("//div[@id='start']/button"));
        startButton.click();

        // ---------------------------------------------------------------------------------------------
        // Verification
        // ---------------------------------------------------------------------------------------------

        // Check expected message (with explicit wait)
        WebElement finishElement = mDriver.findElement(By.id("finish"));
        WebDriverWait wait = new WebDriverWait(mDriver, 10);
        wait.until(ExpectedConditions.visibilityOf(finishElement));
        String finishText = finishElement.getText();
        Assert.assertTrue(finishText.contains("Hello World!"), "Finish text: " + finishText);
    }

    @Test(priority = 2)
    public void timeoutTest() {

        // ---------------------------------------------------------------------------------------------
        // Execution
        // ---------------------------------------------------------------------------------------------

        Util.log("Starting notVisibleTest");

        // Open test page
        String url = "https://the-internet.herokuapp.com/dynamic_loading/1";
        mDriver.get(url);
        Util.log("Open test page");

        // Press Start button
        WebElement startButton = mDriver.findElement(By.xpath("//div[@id='start']/button"));
        startButton.click();

        // ---------------------------------------------------------------------------------------------
        // Verification
        // ---------------------------------------------------------------------------------------------

        // Check finish text timeout
        WebElement finishElement = mDriver.findElement(By.id("finish"));
        try {
            WebDriverWait wait = new WebDriverWait(mDriver, 2);
            wait.until(ExpectedConditions.visibilityOf(finishElement));
        } catch (TimeoutException e) {
            Util.log("Exception caught: " + e.getMessage());
            Util.sleep(3000); // add more wait
        }
        String finishText = finishElement.getText();
        Assert.assertTrue(finishText.contains("Hello World!"), "Finish text: " + finishText);
    }

    @Test(priority = 3)
    public void noSuchElementTest() {

        // ---------------------------------------------------------------------------------------------
        // Execution
        // ---------------------------------------------------------------------------------------------

        Util.log("Starting noSuchElementTest");

        // Open test page
        String url = "https://the-internet.herokuapp.com/dynamic_loading/2";
        mDriver.get(url);
        Util.log("Open test page");

        // Press Start button
        WebElement startButton = mDriver.findElement(By.xpath("//div[@id='start']/button"));
        startButton.click();

        // ---------------------------------------------------------------------------------------------
        // Verification
        // ---------------------------------------------------------------------------------------------

        // Check expected message (with explicit wait)
        WebDriverWait wait = new WebDriverWait(mDriver, 10);
        Assert.assertTrue(
                wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("finish"), "Hello World!")),
                "Couldn't verify expected text"
        );
    }

    @AfterMethod(alwaysRun = true)
    private void tearDown() {
        mDriver.quit();
    }

}
