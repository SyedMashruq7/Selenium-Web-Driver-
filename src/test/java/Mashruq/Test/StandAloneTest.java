
package Mashruq.Test;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Mashruq.PageObject.CartPage;
import Mashruq.PageObject.CheckOut;
import Mashruq.PageObject.ConfirmationPage;
import Mashruq.PageObject.product;
import Mashruq.TestComponent.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class StandAloneTest extends BaseTest {

	@Test(dataProvider = "getData" , groups = {"Sanity", "Master"}) 
	@Severity(SeverityLevel.CRITICAL)
	public void submitOrder(String email, String password, String productName) throws IOException {



		logger.info("Started testing | Product: {} | Email: {}", productName, email);

		product p = lp.loginApp(email, password);

		logger.info("User successfully logged in.");

		p.addProductToCart(productName);

		logger.info("Adding product {} to cart.", productName);

		CartPage c = p.goToCartPage();

		Boolean match = c.verifyProductDisplay(productName);

		Assert.assertTrue(match);

		logger.info("{} product added successfully to cart.", productName);

		CheckOut co = c.goToCheckout();

		co.selectCountry("india");

		ConfirmationPage cp = co.submitOrder();

		logger.info("Submitted the order {} successfully.", productName);


		String confirmMessage = cp.getConfirmationMessage();

		Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");
	}

	@DataProvider
	public Object[][] getData() {

		return new Object[][] { { "20ne1a05f7@gmail.com", "Mashruq@123", "ZARA COAT 3" },
				{ "sjilani476@gmail.com", "Reshma@123", "ZARA COAT 3" } };
	}
}
