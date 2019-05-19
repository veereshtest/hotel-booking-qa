package utilities;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserUtils {
	
	private static Logger log = Logger.getLogger(BrowserUtils.class.getName()); 
	public static WebDriver driver;
	
    public static void getBrowser(String browser) throws Exception {
		String os = System.getProperty("os.name").toLowerCase();
		log.info("Current opertaing System is: " + os);
		
		if (os.contains("mac")) {
			if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", "drivers/firefox/geckodriver");
				driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "drivers/chrome/chromedriver");
				driver = new ChromeDriver();
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
