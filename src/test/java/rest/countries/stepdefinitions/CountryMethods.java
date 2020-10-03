package rest.countries.stepdefinitions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import rest.main.Country;

import javax.xml.ws.Response;
import java.util.List;
import rest.countries.stepdefinitions.CountryName;

public class CountryMethods {


    public static List<Country> method1 (String responseBody){
        Gson gson = new Gson();
        List<Country> retCountryList = gson.fromJson(responseBody, new TypeToken<List<Country>>(){}.getType());

        return  retCountryList;
    }




}
