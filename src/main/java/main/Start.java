package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pom.HomePage;
import pom.PurchasePage;
import pom.ReserveFlightPage;

public class Start {

	WebDriver driver;
	FileInputStream readProperty;
	HomePage homePage;
	ReserveFlightPage reserveFlightPage;
	PurchasePage purchasePage;

	@BeforeTest
	private void prepareClassProperties() throws IOException {
		readProperty = new FileInputStream(
				"C:\\Users\\G525585\\eclipse-workspace\\AutomateBlazeDemoWebApplication\\src\\main\\java\\properties\\generalProperties.properties");
		Properties prop = new Properties();
		prop.load(readProperty);

		System.setProperty("webdriver.gecko.driver", prop.getProperty("firefoxdriver"));
		System.setProperty("webdriver.chrome.driver", prop.getProperty("chromedriver"));

		driver = new ChromeDriver();
		homePage = new HomePage(driver);
	}

	@Test(priority = 1)
	private void startApplication() throws InterruptedException {

		// Mazimize current window
		driver.manage().window().maximize();

		// navigate to website
		driver.get("https://blazedemo.com/");

		// wait for 5 sec
		Thread.sleep(5000);
	}

	@Test(priority = 2)
	private void searchFlights() throws InterruptedException {

		Select departureCountry = new Select(homePage.departureCity);

		departureCountry.selectByVisibleText("San Diego");

		Select destinationCountry = new Select(homePage.destinationCity);

		destinationCountry.selectByVisibleText("London");

		homePage.findFlights.click();

		// wait for 4 sec
		Thread.sleep(4000);

		Assert.assertEquals(driver.getPageSource().contains("Airline"), true);
	}

	@Test(priority = 3)
	private void chooseFlight() throws InterruptedException {

		reserveFlightPage = new ReserveFlightPage(driver);

		reserveFlightPage.chooseFlight.click();

		Assert.assertEquals(driver.getPageSource().contains("Your flight from TLV to SFO has been reserved"), true);
	}

	@Test(priority = 4)
	private void purchaseFlight() throws InterruptedException {

		purchasePage = new PurchasePage(driver);

		purchasePage.clientName.sendKeys("Shady Ahmed");

		purchasePage.clientAddress.sendKeys("13 Mostafa kamel street");

		purchasePage.clientCity.sendKeys("El Sharkia");

		// declare javascript executer object
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,100)", "");
		
		purchasePage.clientState.sendKeys("Abu Kabir");
		
		purchasePage.clientZipCode.sendKeys("44671");
		
		Select cardType=new Select(purchasePage.cardType);
		cardType.selectByVisibleText("American Express");
		
		purchasePage.creditCardNumber.sendKeys("123456789");
		
		purchasePage.creditCardMonth.sendKeys("9");
		
		purchasePage.creditCardYear.sendKeys("1997");
		
		js.executeScript("window.scrollBy(0,100)", "");
		
		purchasePage.nameOnCard.sendKeys("Shady Gomaa");
		
		purchasePage.rememberMe.click();
		
		purchasePage.purchaseFlight.click();

		Assert.assertEquals(driver.getPageSource().contains("Your flight from TLV to SFO has been reserved"), true);
	}

	@AfterTest
	private void closeApplication() {
		driver.quit();
	}

}
