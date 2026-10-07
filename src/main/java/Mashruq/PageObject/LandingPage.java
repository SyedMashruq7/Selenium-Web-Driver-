package Mashruq.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement emailWebElement;

	@FindBy(id = "userPassword")
	WebElement passwordWebElement;

	@FindBy(id = "login")
	WebElement loginWebElement;
	
	@FindBy(xpath = "//h3[normalize-space()='Automation']")
	WebElement automationText;
	
	@FindBy(xpath="//button[normalize-space()='Sign Out']")
	WebElement signout;
	
	By AutomationText = By.xpath("//h3[normalize-space()='Automation']");
	
	public void goTo(String url) {
		driver.get(url);
	}

	public product loginApp(String email, String password) {
		emailWebElement.sendKeys(email);
		passwordWebElement.sendKeys(password);
		loginWebElement.click();
		product p = new product(driver);
		return p;

	}
	
	public boolean verifyLoginSuccess(String email, String password) {
		
		emailWebElement.sendKeys(email);
		passwordWebElement.sendKeys(password);
		loginWebElement.click();
		
		waitForElementToAppear(AutomationText);
		
	    return waitForElementToAppear(AutomationText);

	}
	
	public void clickLogout() {
		
		waitForElementToBeClickable(signout);
		
		signout.click();
		
	}

}
