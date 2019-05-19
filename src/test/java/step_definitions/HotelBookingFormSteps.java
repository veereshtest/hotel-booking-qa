package step_definitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageobjects.HotelBookingPage;
import utilities.BrowserUtil;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;


public class HotelBookingManagementSteps
{
    WebDriver driver;
    HotelBookingPage hotelBookingPage;
    WebDriverWait wait;
 
    public HotelBookingManagementSteps()
    {
		 driver = BrowserUtil.driver;
		 hotelBookingPage = new HotelBookingPage(driver);
		 wait=new WebDriverWait(driver, 5, 100);
	}    
    
    @Given("^I Navigated to the hotel bookings form page$")
    public void i_Navigated_to_the_hotel_bookings_form_page() throws Throwable 
    {
    	  // Opening Hotel Booking Page URL
          hotelBookingPage.go_to_url();
          // Verify hotel booking form page is loaded
          assertThat(driver.getTitle(), equalToIgnoringCase("Hotel Booking Form"));

    }
    
    @Given("^I Enter the booking information with \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\"$")
    public void i_Enter_the_booking_information_with(String firstName, String lastName, String price, String deposit, String checkin, String checkout) throws Throwable {
    	// This function enters booking details in the form
    	hotelBookingPage.fillin_booking_details(firstName,lastName,price, deposit, checkin, checkout);
    }

    @When("^I Click the Save button$")
    public void i_Click_the_Save_button() throws Throwable {
    	// This function performs click action on save button
        hotelBookingPage.click_save_button();
        driver.navigate().refresh();
        
    }
    
    @Then("^I Should be able to see the new booking entry with FistName as \"([^\"]*)\" in the records list$")
    public void i_Should_be_able_to_see_the_new_booking_entry_with_FistName_as_in_the_records_list(String firstName) throws Throwable {
    	hotelBookingPage.select_record_from_list(firstName);
    	// Asserting first name and last name to make sure the record is created
    	assertThat(hotelBookingPage.get_name_from_record(), equalTo(firstName));
    	
         
    }

    @When("^I Click the Delete button on record with \"([^\"]*)\"$")
    public void i_Click_the_Delete_button_on_record_with(String firstName) throws Throwable {
    	driver.navigate().refresh();
    	// Select and store record id that will be used to delete the record later
    	hotelBookingPage.select_record_from_list(firstName);
    	// Perform delete on record
    	hotelBookingPage.click_delete_button();
    }

    @Then("^I Should be able to see the new booking entry is deleted from the records list$")
    public void i_Should_be_able_to_see_the_new_booking_entry_is_deleted_from_the_records_list() throws Throwable {
        driver.navigate().refresh();
        // Explicit wait to check element is not present after deletion
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(hotelBookingPage.get_record_by_id())));
    }

}
