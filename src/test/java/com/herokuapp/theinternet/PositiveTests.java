package com.herokuapp.theinternet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class PositiveTests {

	@Test
	public void loginTest() {

		System.out.println("Starting login test");

		// Create web driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
		WebDriver driver = new ChromeDriver();

		// Maximize browser window
		driver.manage().window().maximize();

		// Open test page
		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);
		System.out.println("Open test page");

		// Enter password

		// Click login button

		// Verification
		// - new url
		// - logout button is visible
		// - succesful login message

		// Close browser
		driver.quit();

	}

}
