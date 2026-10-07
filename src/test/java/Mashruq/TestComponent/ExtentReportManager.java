
package Mashruq.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.qameta.allure.Allure;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;

	@Override
	public void onStart(ITestContext context) {

		System.out.println("onStart Executed");
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report-" + timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/Reports/MyReport1.html");

		// sparkReporter = new ExtentSparkReporter(".\\allure-results\\ " + repName);

		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setReportName("Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}

	}

	@Override
	public void onTestSuccess(ITestResult result) {

		System.out.println("onTestSuccess Executed");

		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());

		test.log(Status.PASS, "Test Case Passed: " + result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {

		System.out.println("onTestFailure Executed");
		System.out.println("========== TEST FAILURE ==========");
		System.out.println("Test Name: " + result.getName());
		System.out.println("Failure Cause:");

		result.getThrowable().printStackTrace();

		System.out.println("==================================");

		test = extent.createTest(result.getTestClass().getName());

		test.assignCategory(result.getMethod().getGroups());

		test.log(Status.FAIL, "Test Case Failed is : " + result.getName());

		test.log(Status.FAIL, result.getThrowable().getMessage());

		try {

			// Get the actual running test instance
			BaseTest testClass = (BaseTest) result.getInstance();

			// Screenshot path remains String
			String imgPath = testClass.captureScreen(result.getName());

			// ==========================
			// EXTENT REPORT
			// ==========================

			test.addScreenCaptureFromPath("screenshots/" + new File(imgPath).getName());

			// ==========================
			// ALLURE REPORT
			// ==========================

			try (FileInputStream inputStream = new FileInputStream(imgPath)) {

				Allure.addAttachment("Failure Screenshot", "image/png", inputStream, ".png");
			}

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		System.out.println("onTestSkipped Executed: " + result.getName());

		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());

		test.log(Status.SKIP, "Test Case Skipped: " + result.getName());
	}

	@Override
	public void onFinish(ITestContext context) {

		System.out.println("onFinish Executed");

		extent.flush();
	}
}
