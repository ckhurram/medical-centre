Feature: Appointment Booking
  As a registered patient
  I want to book an appointment with a medic
  So that I can receive medical care

  Scenario: Successful appointment booking
    Given I am logged in as a patient
    And a medic is available on "2026-12-01" at "10:00"
    When I book an appointment
    Then I see a booking confirmation

  Scenario: Double booking prevention
    Given a medic is already booked on "2026-12-01" at "10:00"
    When I attempt to book the same slot
    Then I see an error message about slot conflict