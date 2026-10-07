package Mashruq.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class CheckOut extends AbstractComponent {

	WebDriver driver;

	public CheckOut(WebDriver driver) {

		super(driver);

		this.driver = driver;

	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;

	@FindBy(css = ".action__submit")
	WebElement submit;

	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;

	By results = By.cssSelector(".ta-results");

	public void selectCountry(String countryName) {

		Actions a = new Actions(driver);

		a.sendKeys(country, countryName).build().perform();

		// Wait for the dropdown to appear
		waitForElementToAppear(results);
		


		// Scroll the country option into view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center', inline:'nearest'});",
				selectCountry);
		// Wait for the actual option
		waitForElementToBeClickable(selectCountry);


		
		selectCountry.click();
	}

	public ConfirmationPage submitOrder() {

		// Wait for submit button
		waitForElementToBeClickable(submit);

		// Scroll submit button into view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", submit);

		// Wait again after scrolling
		waitForElementToBeClickable(submit);

		// Click submit
		submit.click();

		return new ConfirmationPage(driver);
	}
}