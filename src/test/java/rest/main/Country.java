package rest.main;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Country {

        public String name;
        public List<String> topLevelDomain;
        public String alpha2Code;
        public String alpha3Code;
        public List<String> callingCodes;
        public String capital;
        public List<String> altSpellings;
        public String region;
        public String subregion;
        public int population;
        public List<Double> latlng;
        public String demonym;
        public double area;
        public double gini;
        public List<String> timezones;
        public List<String> borders;
        public String nativeName;
        public String numericCode;
        public List<Currency> currencies;
        public List<Language> languages;
        public Translations translations;
        public String flag;
        public List<Object> regionalBlocs;
        public String cioc;
        public String message;
        public String status;



    Country(){}

        public String getStatus() {
            return message;
        }
        public String getMessage() {
            return message;
        }

        public String getName() {
            return name;
        }

        public List<String> getTopLevelDomain() {
            return topLevelDomain;
        }

        public String getAlpha2Code() {
            return alpha2Code;
        }

        public String getAlpha3Code() {
            return alpha3Code;
        }

        public List<String> getCallingCodes() {
            return callingCodes;
        }

        public String getCapital() {
            return capital;
        }

        public List<String> getAltSpellings() {
            return altSpellings;
        }

        public String getRegion() {
            return region;
        }

        public String getSubregion() {
            return subregion;
        }

        public Integer getPopulation() {
            return population;
        }

        public List<Double> getLatlng() {
            return latlng;
        }

        public String getDemonym() {
            return demonym;
        }

        public Double getArea() {
            return area;
        }

        public Double getGini() {
            return gini;
        }

        public List<String> getTimezones() {
            return timezones;
        }

        public List<String> getBorders() {
            return borders;
        }

        public String getNativeName() {
            return nativeName;
        }

        public String getNumericCode() {
            return numericCode;
        }


    public static class Root{
        @JsonProperty("CountryList")
        public List<Country> CountryList;
    }


}
