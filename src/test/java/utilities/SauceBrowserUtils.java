package utilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.saucelabs.saucerest.SauceREST;
import cucumber.api.Scenario;

public class SauceBrowserUtils {
	
	public static WebDriver driver;
	public Scenario scenario = null;
	public WebDriverWait wait;
	public SauceUtils sauceUtils;
    String sessionId;

	private static Logger log = Logger.getLogger(SauceBrowserUtils.class.getName());

	// export SAUCE_USERNAME="ven21"
	// export SAUCE_ACCESS_KEY="f9fdbc35-9559-4522-ab56-1bf75bbb02f3"
	// mvn clean install test

	// private final String BASE_URL = "https://www.saucedemo.com";

	public void initiateSauceBrowser(String browser) throws Exception {
		String username = System.getenv("SAUCE_USERNAME");
		String accesskey = System.getenv("SAUCE_ACCESS_KEY");

		String os = System.getProperty("os.name").toLowerCase();
		log.info("Current opertaing System is: " + os);

		if (os.contains("mac")) {
			if (browser.equalsIgnoreCase("firefox")) {

				FirefoxOptions caps = new FirefoxOptions();
				caps.setCapability("version", "57.0");

				// Create a map of capabilities called "sauce:options", which contain the info
				// necessary to run on Sauce
				// Labs, using the credentials stored in the environment variables. Also runs
				// using the new W3C standard.
				MutableCapabilities sauceOptions = new MutableCapabilities();
				sauceOptions.setCapability("username", username);
				sauceOptions.setCapability("accessKey", accesskey);
				sauceOptions.setCapability("seleniumVersion", "3.141.59");

				// Assign the Sauce Options to the base capabilities
				caps.setCapability("sauce:options", sauceOptions);
				// Create a new RemoteWebDriver, which will initialise the test execution on
				// Sauce Labs servers
				String SAUCE_REMOTE_URL = "https://ondemand.eu-central-1.saucelabs.com/wd/hub";

				driver = new RemoteWebDriver(new URL(SAUCE_REMOTE_URL), caps);
				sessionId = ((RemoteWebDriver) driver).getSessionId().toString();
				wait = new WebDriverWait(driver, 10);

				SauceREST sauceREST = new SauceREST(username, accesskey);
				sauceUtils = new SauceUtils(sauceREST);

			} else if (browser.equalsIgnoreCase("chrome")) {
				ChromeOptions options1 = new ChromeOptions();
				options1.setCapability("version", "74.0");
				options1.setExperimentalOption("w3c", true);

				// Create a map of capabilities called "sauce:options", which contain the info
				// necessary to run on Sauce
				// Labs, using the credentials stored in the environment variables. Also runs
				// using the new W3C standard.
				MutableCapabilities sauceOptions = new MutableCapabilities();
				sauceOptions.setCapability("username", username);
				sauceOptions.setCapability("accessKey", accesskey);
				sauceOptions.setCapability("seleniumVersion", "3.141.59");

				// Assign the Sauce Options to the base capabilities
				options1.setCapability("sauce:options", sauceOptions);

				// Create a new RemoteWebDriver, which will initialise the test execution on
				// Sauce Labs servers
				String SAUCE_REMOTE_URL = "https://ondemand.eu-central-1.saucelabs.com/wd/hub";

				driver = new RemoteWebDriver(new URL(SAUCE_REMOTE_URL), options1);
				sessionId = ((RemoteWebDriver) driver).getSessionId().toString();
				wait = new WebDriverWait(driver, 10);

				SauceREST sauceREST = new SauceREST(username, accesskey);
				sauceUtils = new SauceUtils(sauceREST);
			} else if (browser.equalsIgnoreCase("headless")) {
				ChromeOptions options2 = new ChromeOptions();
				options2.setCapability("version", "74.0");
				options2.setCapability("platform", "Windows 10");
				options2.setExperimentalOption("w3c", true);
				options2.addArguments("--headless");
				options2.addArguments("window-size=1200x600");

				// Create a map of capabilities called "sauce:options", which contain the info
				// necessary to run on Sauce
				// Labs, using the credentials stored in the environment variables. Also runs
				// using the new W3C standard.
				MutableCapabilities sauceOptions = new MutableCapabilities();
				sauceOptions.setCapability("username", username);
				sauceOptions.setCapability("accessKey", accesskey);
				sauceOptions.setCapability("seleniumVersion", "3.141.59");

				// Assign the Sauce Options to the base capabilities
				options2.setCapability("sauce:options", sauceOptions);

				// Create a new RemoteWebDriver, which will initialise the test execution on
				// Sauce Labs servers
				String SAUCE_REMOTE_URL = "https://ondemand.eu-central-1.saucelabs.com/wd/hub";

				driver = new RemoteWebDriver(new URL(SAUCE_REMOTE_URL), options2);
				sessionId = ((RemoteWebDriver) driver).getSessionId().toString();
				wait = new WebDriverWait(driver, 10);

				SauceREST sauceREST = new SauceREST(username, accesskey);
				sauceUtils = new SauceUtils(sauceREST);
			}

		} else if (os.contains("win")) {
			if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", "drivers/firefox/geckodriver.exe");
				driver = new FirefoxDriver();

			} else if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "drivers/chrome/chromedriver.exe");
				driver = new ChromeDriver();
			}

		} else {
			throw new Exception("OS neither mac nor windows");
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

}
