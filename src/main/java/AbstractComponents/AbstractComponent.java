package AbstractComponents;

import java.time.Duration;
import org.openqa.selenium.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Mashruq.PageObject.CartPage;

public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[routerLink*='cart']")
	WebElement cartHeader;

	@FindBy(xpath = "//button[text()='Checkout']")
	WebElement checkout;

	@FindBy(css =".ngx-spinner-overlay")
	WebElement spinner;

	public boolean waitForElementToAppear(By findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
			return true;

		} catch (TimeoutException e) {
			return false;
		}
	}

	public void waitForElementToBeClickable(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForElementToDissapear(WebElement ele) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		wait.until(ExpectedConditions.invisibilityOf(ele));

	}

	public CartPage goToCartPage() {
		
		waitForElementToDissapear(spinner);
		waitForElementToBeClickable(cartHeader);
		cartHeader.click();
		CartPage c = new CartPage(driver);
		return c;
	}

}
