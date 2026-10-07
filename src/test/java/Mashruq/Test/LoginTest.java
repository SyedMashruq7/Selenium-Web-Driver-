package Mashruq.Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import DataDriven.DataProviders;
import Mashruq.TestComponent.BaseTest;

public class LoginTest extends BaseTest {

	@Test(dataProvider = "getLoginData", dataProviderClass = DataProviders.class , groups = {"DataDriven", "Master"})
	public void verify_Login(String username, String password, String result) {

		logger.info("Started Testing Login Test.");

//		Assert.assertTrue(lp.verifyLoginSuccess(username,password),"Login failed: Automation heading is not displayed.");

		if (result.equalsIgnoreCase("Valid")) {

			if (lp.verifyLoginSuccess(username, password)) {
				System.out.println("Verify Login is Executed and user logged in");
				lp.clickLogout();
				logger.info("User Logout is successfully Executed");

			} else {

				System.out.println("Verify Login is Failed");
				Assert.assertTrue(false);
			}

		}
		if(result.equalsIgnoreCase("Invalid")) {
			if (lp.verifyLoginSuccess(username, password)) {
				
				System.out.println("Verify Login is Executed and user logged in");
				lp.clickLogout();
				Assert.assertTrue(false);
				logger.info("User Logout is successfully Executed");
		     }
			else {
				Assert.assertTrue(true);
			}
			
			
		}
		logger.info("Login Test is completed");
	}}
