package rest.countries.stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import rest.main.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountryName {

	private static Response getRespCntry;
	View app = new View();

	@Given("^SEND GET RestCountries name service api endpoint with \"([^\"]*)\"$")
	public void send_GET_RestCountries_name_service_api_endpoint_with(String name) throws Throwable {

		getRespCntry = app.getRespCountryData("name", name);
	}

	@When("^receive valid HTTP response$")
	public void receive_valid_HTTP_response() throws Throwable {

		System.out.println("Response : " + app.printResponse(getRespCntry));

	}

	@Then("^validate \"([^\"]*)\" for given country name and statuscode (\\d+)$")
	public void validate_for_given_country_name_and_statuscode(String expectedCapital, int statuscode)
			throws Throwable {
		JSONArray jarr = new JSONArray(getRespCntry.asString());

		String actualcapital = jarr.getJSONObject(0).get("capital").toString();
		Assert.assertEquals(actualcapital, expectedCapital);
		System.out.println("StatusCode : " + getRespCntry.getStatusCode());
		Assert.assertEquals(statuscode, getRespCntry.getStatusCode());

	}

	@Then("^validate error message \"([^\"]*)\" for given invalid \"([^\"]*)\" and statuscode \"([^\"]*)\"$")
	public void validate_error_message_for_given_invalid_and_statuscode(String msg, String arg2, String statuscode)
			throws Throwable {
		JSONObject jarr = new JSONObject(getRespCntry.asString());
		String actualcapital = jarr.get("message").toString();
		Assert.assertEquals(actualcapital, msg);

		System.out.println("StatusCode : " + getRespCntry.getStatusCode());
		Assert.assertEquals(Integer.parseInt(statuscode), getRespCntry.getStatusCode());
	}


	@When("^validate \"([^\"]*)\" of given country code and statuscode (\\d+)$")
	public void validate_of_given_country_code_and_statuscode(String expectedCapital, int statuscode) throws Throwable {
		// Write code here that turns the phrase above into concrete actions

		JSONObject jarr = new JSONObject(getRespCntry.asString());
		String actualcapital = jarr.get("capital").toString();
		Assert.assertEquals(actualcapital, expectedCapital);

		System.out.println("StatusCode : " + getRespCntry.getStatusCode());
		Assert.assertEquals(statuscode, getRespCntry.getStatusCode());

	}

	@Given("^SEND DELETE RestCountries code service api endpoint with given CountryName$")
	public void send_DELETE_RestCountries_code_service_api_endpoint_with_given_CountryName() throws Throwable {
		RestAssured.baseURI = app.getEndpointURL("name");
		RequestSpecification httpRequest = RestAssured.given();
		getRespCntry = httpRequest.request(Method.DELETE, "/" + "ukraine");
	}

	@When("^receive invalid HTTP response$")
	public void receive_invalid_HTTP_response() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("Response : " + app.printResponse(getRespCntry));
	}

	@Then("^validate statuscode (\\d+)$")
	public void validate_statuscode(int statuscode) throws Throwable {
		System.out.println("StatusCode : " + getRespCntry.getStatusCode());
		Assert.assertEquals(statuscode, getRespCntry.getStatusCode());
	}

	@Then("^validate Capital cities for given \"([^\"]*)\" and statuscode (\\d+)$")               ///////
	public void validate_Capital_cities_for_given_and_statuscode(String arg1, int arg2) throws Throwable {

		// Expected
		Map<String, String> ExpectedResponse = new HashMap<String, String>();
		ExpectedResponse.put("Ukraine", "Kiev");

		Map<String, String> ActualResponse = new HashMap<String, String>();
		String responseBody = getRespCntry.getBody().asString();

		List<String> cities = new ArrayList<String>();
		JSONArray jarr = new JSONArray(responseBody);

		for (int i = 0; i < jarr.length(); i++) {

			String name = jarr.getJSONObject(i).get("name").toString();
			String capital = jarr.getJSONObject(i).get("capital").toString();
			ActualResponse.put(name, capital);

		}

		System.out.println(ActualResponse);
		System.out.println(ExpectedResponse);
		Boolean compare = ActualResponse.equals(ExpectedResponse);
		Assert.assertEquals(true, compare);

	}

	private Boolean CompareHashMaps(Map<String, String> actualResponse, Map<String, String> expectedResponse) {
		return null;
	}

}
