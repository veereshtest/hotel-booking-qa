package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class HotelBookingPage {
	
	WebDriver driver;
	String nameXpath,idXpath,id,deleteXpath;
	
	public HotelBookingPage(WebDriver driver)
	{
	this.driver = driver;
	PageFactory.initElements(driver, this);
    }    

	
	@FindBy(how=How.ID, using="firstname")
	private WebElement txt_firstname;
	
	@FindBy(how=How.ID, using="lastname")
	private WebElement txt_lastname;
	
	@FindBy(how=How.ID, using="totalprice")
	private WebElement txt_totalprice;
	

	@FindBy(how=How.ID, using="depositpaid")
	private WebElement drp_deposit;

	@FindBy(how=How.ID, using="checkin")
	private WebElement txt_checkin;
	
	@FindBy(how=How.ID, using="checkout")
	private WebElement txt_checkout;
	
	@FindBy(how=How.XPATH, using="//input[@value=' Save ']")
	private WebElement btn_save;
	
	@FindBy(how=How.XPATH, using="//input[@value='Delete']")
	private WebElement btn_delete;
	
	
	
	
	public void go_to_url() {
		driver.get("http://hotel-test.equalexperts.io/");
	}

    public void fillin_booking_details(String fName, String lName, String prc, String dpt, String cin, String cout) 
    {
    	txt_firstname.sendKeys(fName);
    	txt_lastname.sendKeys(lName);
    	txt_totalprice.sendKeys(prc);
    	Select drpDeposit =  new Select(drp_deposit);
    	drpDeposit.selectByVisibleText(dpt);
    	txt_checkin.sendKeys(cin);
    	txt_checkout.sendKeys(cout);
    }
    
    public void click_save_button() 
    {
    	btn_save.click();
    }
    
    public void select_record_from_list(String fName)
    {
        nameXpath = String.format("//div[@id='bookings']/div/div/p[contains(text(),'%s')]/ancestor::div[@class='row']",fName);
        id = driver.findElement(By.xpath(nameXpath)).getAttribute("id");
    	idXpath = "//*[@id="+id+"]/div[1]/p";
    }
    
    public String get_name_from_record() {
    	String expectedfName = driver.findElement(By.xpath(idXpath)).getText();
    	return expectedfName;
    }
       
    
    public void click_delete_button() {
    	deleteXpath = String.format("//input[@onclick='deleteBooking(%s)']", id);
    	driver.findElement(By.xpath(deleteXpath)).click();
    }
    
    public String get_record_by_id(){
    	idXpath = "//*[@id="+id+"]/div[1]/p";
    	return idXpath;
    	
    }
    
       
 }

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
