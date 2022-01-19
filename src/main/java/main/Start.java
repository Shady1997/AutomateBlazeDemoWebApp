package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pom.LoginPage;

public class Start {

	WebDriver driver;
	FileInputStream readProperty;
	LoginPage login;

	@BeforeTest
	private void prepareClassProperties() throws IOException {
		readProperty = new FileInputStream(
				"C:\\Users\\G525585\\eclipse-workspace\\AutomateParaBankWebApplication\\src\\main\\java\\properties\\generalProperties.properties");
		Properties prop = new Properties();
		prop.load(readProperty);

		System.setProperty("webdriver.gecko.driver", prop.getProperty("firefoxdriver"));
		System.setProperty("webdriver.chrome.driver", prop.getProperty("chromedriver"));

		driver = new ChromeDriver();
		login = new LoginPage(driver);
	}

	@Test(priority = 1)
	private void startApplication() throws InterruptedException {

		// Mazimize current window
		driver.manage().window().maximize();

		// navigate to website
		driver.get("https://parabank.parasoft.com/parabank/index.htm");

		// wait for 5 sec
		Thread.sleep(5000);
	}

	@Test(priority = 2)
	private void login() throws InterruptedException {

		login.userName.sendKeys("john");

		login.userPassword.sendKeys("demo");

		login.loginButton.click();

		// wait for 10 sec
		Thread.sleep(10000);

		Assert.assertEquals(driver.getPageSource().contains("Accounts Overview"), true);
	}

	@AfterTest
	private void closeApplication() {
		driver.quit();
	}

}
