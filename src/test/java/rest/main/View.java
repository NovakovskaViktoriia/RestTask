package rest.main;

import com.google.gson.*;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
		if (getRespCntry.getStatusCode() == 200) {

			Object json = new JSONTokener(getRespCntry.getBody().asString()).nextValue();
			if (json instanceof JSONObject) {
				JsonObject respcntrydata = getCapitalCityObj(getRespCntry);
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				return gson.toJson(respcntrydata);
			} else if (json instanceof JSONArray) {
				JsonArray respcntrydata = getCapitalCity(getRespCntry);
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				return gson.toJson(respcntrydata);
			}
		} else {
			return getRespCntry.prettyPrint();
		}
		return null;

	}

	public static JsonObject getCapitalCityObj(Response getRespCntry) {
		String responseBody = getRespCntry.getBody().asString();
		JSONObject jarr = new JSONObject(responseBody);

		String name = jarr.get("name").toString();
		//List<String> topLevelDomain;
		String alpha2Code = jarr.get("alpha2Code").toString();
		String alpha3Code = jarr.get("alpha3Code").toString();
		//List<String> callingCodes;
		String capital = jarr.get("capital").toString();
		//List<String> altSpellings;
		String region = jarr.get("region").toString();
		String subregion = jarr.get("subregion").toString();
		Integer population = jarr.getInt("population");
		//List<Double> latlng;
		String demonym = jarr.get("demonym").toString();
		//Double area;
		//Double gini;
		//List<String> timezones;
		//List<String> borders;
		String nativeName = jarr.get("nativeName").toString();
		String numericCode = jarr.get("numericCode").toString();
		//List<Currency> currencies;
		//List<Language> languages;
		//Translations translations;
		String flag = jarr.get("flag").toString();;
		//List<RegionalBloc> regionalBlocs;
		String cioc = jarr.get("cioc").toString();


		JsonObject obj = createJsonObject(name, alpha2Code, alpha3Code, capital, region, subregion, demonym, nativeName, cioc);

		return obj;

	}

	public static JsonArray getCapitalCity(Response getRespCntry) {
		JsonArray respCapitalCity = new JsonArray();

		String responseBody = getRespCntry.getBody().asString();
		JSONArray jarr = new JSONArray(responseBody);

		for (int i = 0; i < jarr.length(); i++) {

			String name = jarr.getJSONObject(i).get("name").toString();
			String alpha2Code = jarr.getJSONObject(i).get("alpha2Code").toString();
			String alpha3Code = jarr.getJSONObject(i).get("alpha3Code").toString();
			String capital = jarr.getJSONObject(i).get("capital").toString();
			String region = jarr.getJSONObject(i).get("region").toString();
			String subregion = jarr.getJSONObject(i).get("subregion").toString();
			String demonym = jarr.getJSONObject(i).get("demonym").toString();
			String nativeName = jarr.getJSONObject(i).get("nativeName").toString();
			String cioc = jarr.getJSONObject(i).get("cioc").toString();

			respCapitalCity.add(createJsonObject(name, alpha2Code, alpha3Code, capital, region, subregion, demonym, nativeName, cioc));

		}
		return respCapitalCity;

	}

	public static JsonObject createJsonObject(String name, String alpha2Code, String alpha3Code, String capital, String region,
											  String subregion, String demonym, String nativeName, String cioc) {
		Map<String, String> respCap = new LinkedHashMap<String, String>();

		respCap.put("name", name);
		respCap.put("alpha2Code", alpha2Code);
		respCap.put("alpha3Code", alpha3Code);
		respCap.put("capital", capital);
		respCap.put("region", region);
		respCap.put("subregion", subregion);
		respCap.put("demonym", demonym);
		respCap.put("nativeName", nativeName);
		respCap.put("cioc", cioc);



		Gson gson = new Gson();
		String json = gson.toJson(respCap, LinkedHashMap.class);
		JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
		return obj;
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
