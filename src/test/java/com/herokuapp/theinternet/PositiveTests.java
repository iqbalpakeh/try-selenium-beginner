package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class PositiveTests {

	@Test
	public void loginTest() {

		log("Starting login test");

		// Create web driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
		WebDriver driver = new ChromeDriver();

		//---------------------------------------------------------------------------------------------
		// Execution
		//---------------------------------------------------------------------------------------------

		// Maximize browser window
		driver.manage().window().maximize();

		// Open test page
		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);
		log("Open test page");
		sleep(2000);

		// Enter username
		WebElement username = driver.findElement(By.id("username"));

		// Enter password
		WebElement password = driver.findElement(By.name("password"));

		// Click login button
		WebElement loginButton = driver.findElement(By.tagName("button"));

		//---------------------------------------------------------------------------------------------
		// Verification
		//---------------------------------------------------------------------------------------------

		// - logout button is visible
		WebElement logoutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));

		// - succesful login message
		WebElement successMessage = driver.findElement(By.cssSelector("#flash"));

		// Close browser
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
