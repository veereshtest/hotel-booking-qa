#Author: Veeresh Palacharla
#Email: VeereshPalacharla@gmail.com
@Regression
Feature: Hotel Bookings Management
  
  In order to manage the hotel bookings
  As an registered user
  I want to create and delete the bookings

  Background: 
    Given I Navigated to the hotel bookings form page

  
  Scenario Outline: User creates a new booking
    And I Enter the booking information with "<firstName>", "<lastName>", "<price>", "<deposit>", "<checkin>", "<checkout>"
    When I Click the Save button
    Then I Should be able to see the new booking entry with FistName as "<firstName>" in the records list

    Examples: 
      | firstName | lastName   | price | deposit | checkin    | checkout   |
      | Veeresh   | Palacharla |   200 | false   | 2019-06-01 | 2019-06-12 |
  
  @Hotelbookings
  Scenario Outline: User deletes a booking
    When I Click the Delete button on record with "<firstName>"
    Then I Should be able to see the new booking entry is deleted from the records list

    Examples: 
      | firstName |
      | Veeresh   |
