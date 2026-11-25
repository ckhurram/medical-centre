Feature: Patient Registration API
  As a patient
  I want to register via the REST API
  So that I can book appointments

  Scenario: Register a new patient successfully
    Given the patient service is running
    When I send a registration request with valid details
    Then the response status should be 201
    And the response should contain a patient id

  Scenario: Reject duplicate email registration
    Given the patient service is running
    And a patient already exists with email "bdd.patient@example.com"
    When I send a registration request with a duplicate email
    Then the response status should be 400
    And the response should contain an error message