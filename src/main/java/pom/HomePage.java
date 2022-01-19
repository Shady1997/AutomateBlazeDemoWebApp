package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "fromPort")
	public WebElement departureCity;

	@FindBy(name = "toPort")
	public WebElement destinationCity;

	@FindBy(xpath = "//input[@value='Find Flights']")
	public WebElement findFlights;

}
