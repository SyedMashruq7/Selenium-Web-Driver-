package Mashruq.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.net.URL;

import org.openqa.selenium.remote.RemoteWebDriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import Mashruq.PageObject.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.*;

public class BaseTest {

	public static WebDriver driver;

	public LandingPage lp;

	public Logger logger;

	public WebDriver initializeDriver(String browserName, String osName) throws IOException {

		Properties prop = new Properties();

		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\AbstractComponents\\GlobalData.properties");

		prop.load(file);
		file.close();

		// execution_env comes from properties file
		String executionEnv = prop.getProperty("execution_env");

		// browser and os come from testng.xml
		if (browserName == null || browserName.isBlank()) {
			throw new RuntimeException("browser parameter is missing in testng.xml");
		}

		if (osName == null || osName.isBlank()) {
			throw new RuntimeException("os parameter is missing in testng.xml");
		}

		if (executionEnv == null || executionEnv.isBlank()) {
			throw new RuntimeException("execution_env is missing in GlobalData.properties");
		}

		System.out.println("=================================");
		System.out.println("Execution Environment : " + executionEnv);
		System.out.println("Browser               : " + browserName);
		System.out.println("OS                    : " + osName);
		System.out.println("=================================");

		// =========================
		// REMOTE EXECUTION
		// =========================

		if (executionEnv.equalsIgnoreCase("remote")) {

			DesiredCapabilities cap = new DesiredCapabilities();

			// OS from testng.xml
			if (osName.equalsIgnoreCase("windows")) {

				cap.setPlatform(Platform.WIN11);

			} else if (osName.equalsIgnoreCase("mac")) {

				cap.setPlatform(Platform.MAC);

			} else if (osName.equalsIgnoreCase("linux")) {

				cap.setPlatform(Platform.LINUX);
			} else {

				throw new RuntimeException("Invalid OS in testng.xml: " + osName);
			}

			// Browser from testng.xml
			switch (browserName.toLowerCase()) {

			case "chrome":
				cap.setBrowserName("chrome");
				break;

			case "edge":
				cap.setBrowserName("MicrosoftEdge");
				break;

			case "firefox":
				cap.setBrowserName("firefox");
				break;

			default:
				throw new RuntimeException("Invalid browser in testng.xml: " + browserName);
			}

			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
		}

		// =========================
		// LOCAL EXECUTION
		// =========================

		else if (executionEnv.equalsIgnoreCase("local")) {

			if (browserName.equalsIgnoreCase("chrome")) {

				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();

			} else if (browserName.equalsIgnoreCase("firefox")) {

				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();

			} else if (browserName.equalsIgnoreCase("edge")) {

				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();

			} else {

				throw new RuntimeException("Invalid browser in testng.xml: " + browserName);
			}
		}

		else {

			throw new RuntimeException("Invalid execution_env in GlobalData.properties: " + executionEnv);
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.manage().window().maximize();

		return driver;
	}

	@BeforeMethod(groups = { "Master", "Sanity", "DataDriven" })
	@Parameters({ "browser", "os" })
	public void launchApplicaion(String browserName, String osName) throws IOException {

		logger = LogManager.getLogger(this.getClass());

		driver = initializeDriver(browserName, osName);

		lp = new LandingPage(driver);

		lp.goTo("https://rahulshettyacademy.com/client/#/auth/login");
	}

	@AfterMethod(groups = { "Master", "Sanity", "DataDriven" })
	public void tearDown() {

		if (driver != null) {
			driver.quit();
		}
	}

	public String captureScreen(String testName) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());

		String directory = System.getProperty("user.dir") + "\\Reports\\screenshots\\";

		File screenshotDirectory = new File(directory);

		if (!screenshotDirectory.exists()) {
			screenshotDirectory.mkdirs();
		}

		String targetFilePath = directory + testName + "_" + timeStamp + ".png";

		TakesScreenshot screenshot = (TakesScreenshot) driver;

		File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);

		File targetFile = new File(targetFilePath);

		java.nio.file.Files.copy(sourceFile.toPath(), targetFile.toPath(),
				java.nio.file.StandardCopyOption.REPLACE_EXISTING);

		return targetFile.getAbsolutePath();
	}
}