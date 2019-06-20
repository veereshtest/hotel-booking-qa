package step_definitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageobjects.HotelBookingPage;
import utilities.BrowserUtils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.apache.log4j.Logger;

public class HotelBookingFormSteps {
	private static Logger log = Logger.getLogger(HotelBookingFormSteps.class.getName());
	WebDriver driver;
	HotelBookingPage hotelBookingPage;
	WebDriverWait wait;

	public HotelBookingFormSteps() {
		driver = BrowserUtils.driver;
		hotelBookingPage = new HotelBookingPage(driver);
		wait = new WebDriverWait(driver, 5, 100);
	}

	@Given("^I Navigated to the hotel bookings form page$")
	public void navigateToFormPage() throws Throwable {
		log.info("Go To URl");
		hotelBookingPage.goToUrl();
		assertThat(driver.getTitle(), equalToIgnoringCase("Hotel Booking Form"));
		log.info("Loaded home page of the application");

	}

	@Given("^I Enter the booking information with \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\"$")
	public void enterBookingInformation(String firstName, String lastName, String price, String deposit,
			String checkin, String checkout) throws Throwable {
		log.info("Enter Booking Information");
		hotelBookingPage.fillBookingDetails(firstName, lastName, price, deposit, checkin, checkout);
		log.info("Booking Information Entered");
	}

	@When("^I Click the Save button$")
	public void saveRecord() throws Throwable {
		log.info("Save the record");
		hotelBookingPage.clickSaveButton();
		log.info("Record saved");
		driver.navigate().refresh();

	}

	@Then("^I Should be able to see the new booking entry with FistName as \"([^\"]*)\" in the records list$")
	public void verifyNewBookingIsCreated(String firstName) throws Throwable {
		hotelBookingPage.selectRecordFromList(firstName);
		assertThat(hotelBookingPage.getNameFromRecord(), equalTo(firstName));
		log.info("Record creation is successfull");

	}

	@Given("^I Create a new booking with \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
	public void creteNewBooking(String firstName, String lastName, String price, String deposit, String checkin,
			String checkout) throws Throwable {
		hotelBookingPage.fillBookingDetails(firstName, lastName, price, deposit, checkin, checkout);
		log.info("Booking information is entered");
		hotelBookingPage.clickSaveButton();
		log.info("Record saved");
		driver.navigate().refresh();
	}

	@When("^I Click the Delete button on record with \"([^\"]*)\"$")
	public void clickDeleteButton(String firstName) throws Throwable {
		driver.navigate().refresh();
		hotelBookingPage.selectRecordFromList(firstName);
		log.info("Record is selcted");
		hotelBookingPage.deleteRecord();
		log.info("Record is deleted");
	}

	@Then("^I Should be able to see the new booking entry is deleted from the records list$")
	public void verifyRecordIsDeleted() throws Throwable {
		driver.navigate().refresh();
		log.info("Verify record is deleted from list");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(hotelBookingPage.getRecordById())));
		log.info("Record deletion is scuccessfull");

	}

}
