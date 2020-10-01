package rest.main;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.List;
import java.util.Scanner;

public class View {
	public static void main(String[] args) {

		// Get input as search by name (full or partial)
		Scanner search = new Scanner(System.in);

		do {
			String getName = null;
			Response getRespCntry = null;
			System.out.println("Enter Country Name (full or partial): ");
			getName = search.nextLine();
			getRespCntry = getRespCountryData("name", getName);
			System.out.println("StatusCode : " + getRespCntry.getStatusCode());
			System.out.println("Response : " + printResponse(getRespCntry));
		} while (true); // loop until user exits the program
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

	public static Response getCapitalCityObj(Response getRespCntry) {
		Country country = getRespCntry.getBody().as(Country.class);
		Gson gson = new Gson();
		List<Country> retCountryList = gson.fromJson((JsonElement) getRespCntry, new TypeToken<List<Country>>(){}.getType());
		return getRespCntry;
	}

	public static List getCapitalCity(Response getRespCntry) {
		Gson gson = new Gson();
		List<Country> returnedCountries = gson.fromJson((JsonElement) getRespCntry, new TypeToken<List<Country>>(){}.getType());
		return returnedCountries;
	}

	public static Country createJsonObject(Response getRespCntry){
		Country returnedCountry = getRespCntry.getBody().as(Country.class);
		return returnedCountry;
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