package utilities;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class BrowserUtil {
	
	public static WebDriver driver;
	
	public static void getBrowser(String browser) throws Exception{
		
		
			if(browser.equalsIgnoreCase("firefox")){
				
				System.setProperty("webdriver.gecko.driver", "drivers/firefox/geckodriver");
		    	driver = new FirefoxDriver();
				}
				
				else if(browser.equalsIgnoreCase("chrome")){
					System.setProperty("webdriver.chrome.driver", "drivers/chrome/chromedriver");
			    	driver = new ChromeDriver();
                   }
				
						else if(browser.equalsIgnoreCase("Edge")){
							
							driver = new EdgeDriver();
						}
				else{
					//If no browser passed throw exception
					throw new Exception("Browser is not correct");
				}
			
			driver.manage().deleteAllCookies();
	    	driver.manage().window().maximize();
	    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	    }
	
}
