package Mashruq.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;

	@FindBy(css = ".cartSection h3")
	List<WebElement> productTitles;

	public Boolean verifyProductDisplay(String productName) {
		Boolean match = productTitles.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
	}

	public CheckOut goToCheckout() {
		WebElement checkout = driver.findElement(By.xpath("//button[text()='Checkout']"));
		System.out.println("Trying to fetch Check out Button in Cart Page.");

		((JavascriptExecutor) driver)
				.executeScript("arguments[0].scrollIntoView({behavior:'instant', block:'center'});", checkoutEle);

//		waitForElementToAppear(By.xpath("//button[text()='Checkout']"));
		waitForElementToBeClickable(checkout);
		checkout.click();
		System.out.println("Check out Button click executed successfullt in Cart Page.");

		return new CheckOut(driver);
	}

}
