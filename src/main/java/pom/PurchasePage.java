package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PurchasePage {

	WebDriver driver;

	public PurchasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='inputName']")
	public WebElement clientName;

	@FindBy(xpath = "//input[@id='address']")
	public WebElement clientAddress;

	@FindBy(xpath = "//input[@id='city']")
	public WebElement clientCity;

	@FindBy(xpath = "//input[@id='state']")
	public WebElement clientState;

	@FindBy(xpath = "//input[@id='zipCode']")
	public WebElement clientZipCode;

	@FindBy(name = "cardType")
	public WebElement cardType;

	@FindBy(xpath = "//input[@id='creditCardNumber']")
	public WebElement creditCardNumber;

	@FindBy(xpath = "//input[@id='creditCardMonth']")
	public WebElement creditCardMonth;

	@FindBy(xpath = "//input[@id='creditCardYear']")
	public WebElement creditCardYear;

	@FindBy(xpath = "//input[@id='nameOnCard']")
	public WebElement nameOnCard;

	@FindBy(xpath = "//label[@class='checkbox']")
	public WebElement rememberMe;

	@FindBy(xpath = "//input[@value='Purchase Flight']")
	public WebElement purchaseFlight;
}
