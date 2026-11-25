package com.ew.medicalcentre.patient.bdd;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class PatientRegistrationSteps {

    private RequestSpecification request;
    private Response response;

    @Given("the patient service is running")
    public void the_patient_service_is_running() {
        RestAssured.baseURI = System.getenv().getOrDefault("PATIENTS_SERVICE_URL", "http://localhost:8080");
        request = given().contentType("application/json");
    }

    @When("I send a registration request with valid details")
    public void i_send_a_registration_request_with_valid_details() {
        request.body("{"
            + "\"email\": \"bdd.patient@example.com\","
            + "\"firstName\": \"BDD\","
            + "\"lastName\": \"Patient\","
            + "\"dateOfBirth\": \"1985-03-20\","
            + "\"phone\": \"+15551234567\""
            + "}");
        response = request.when().post("/api/v1/patients");
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(Integer statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the response should contain a patient id")
    public void the_response_should_contain_a_patient_id() {
        response.then().body("patientId", notNullValue());
    }

    @When("I send a registration request with a duplicate email")
    public void i_send_a_registration_request_with_a_duplicate_email() {
        request.body("{"
            + "\"email\": \"bdd.patient@example.com\","
            + "\"firstName\": \"BDD\","
            + "\"lastName\": \"Patient\""
            + "}");
        response = request.when().post("/api/v1/patients");
    }

    @Then("the response should contain an error message")
    public void the_response_should_contain_an_error_message() {
        response.then().body("detail", notNullValue());
    }
}