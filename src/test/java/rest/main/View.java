package rest.main;

import com.google.gson.*;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


import java.util.Scanner;

public class View {
	public static void main(String[] args) {

		Scanner search = new Scanner(System.in);
		do {
			String getName = null;
			Response getRespCntry = null;
			System.out.println("Enter Country Name (full or partial): ");
			getName = search.nextLine();
			getRespCntry = getRespCountryData("name", getName);
			System.out.println("StatusCode : " + getRespCntry.getStatusCode());
			System.out.println("Response : " + printResponse(getRespCntry));
		} while (true);
	}

	public static String printResponse(Response getRespCntry) {
		String error = "Try again, please)";
		if (getRespCntry.getStatusCode() == 200) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonParser jp = new JsonParser();
			JsonElement je = jp.parse(getRespCntry.getBody().asString());
			String prettyJsonString = gson.toJson(je);
			return prettyJsonString;
		}
		else return error;
	}

	public static Response getRespCountryData(String searchBy, String Value) {

		RestAssured.baseURI = getEndpointURL(searchBy);
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/" + Value);
		return response;
	}

	public static String getEndpointURL(String searchBy) {
		String EndpointURL = null;
		if (searchBy.equalsIgnoreCase("name"))
			EndpointURL = "https://restcountries.eu/rest/v2/name";
		return EndpointURL;
	}

}