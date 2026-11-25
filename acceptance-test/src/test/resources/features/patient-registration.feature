Feature: Patient Registration
  As a new patient
  I want to register for the medical centre
  So that I can book appointments

  Scenario: Successful patient registration
    Given the patient registration page is open
    When I submit valid registration details
    Then I see a success confirmation message

  Scenario: Duplicate email registration
    Given a patient already exists with email "test@example.com"
    When I attempt to register with "test@example.com"
    Then I see an error message about duplicate email