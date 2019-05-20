package step_definitions;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import utilities.BrowserUtils;

public class Hooks {

	private static Logger log = Logger.getLogger(Hooks.class.getName());
	public static String browser = System.getProperty("browser");

	/**
	 * Load browser based on the selection
	 */
	@Before
	public void init() throws Exception {
		String browser = System.getProperty("browser");

		if (browser == null) {
			browser = "chrome";
		}
		try {
			if (browser.equalsIgnoreCase("Firefox")) {
				BrowserUtils.getBrowser("firefox");
			} else if (browser.equalsIgnoreCase("Chrome")) {
				BrowserUtils.getBrowser("chrome");
			} else {
				throw new Exception("Browser is not supported");
			}

		} catch (WebDriverException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Embed a screenshot in test report if test is marked as failed
	 */
	@After
	public void embedScreenshot(Scenario scenario) {

		if (scenario.isFailed()) {
			try {
				scenario.write("Current Page URL is " + BrowserUtils.driver.getCurrentUrl());
				byte[] screenshot = ((TakesScreenshot) BrowserUtils.driver).getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png");
			} catch (WebDriverException somePlatformsDontSupportScreenshots) {
				log.error(somePlatformsDontSupportScreenshots.getMessage());
			}

		}
		BrowserUtils.driver.quit();

	}

}