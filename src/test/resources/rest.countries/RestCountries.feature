Feature: Automate tests of Rest Countries API
  Description : Positive and Negative Scenarios

  Scenario Outline: Get Capitalcity of given country name
    Given SEND GET RestCountries name service api endpoint with "<countryname>"
    When receive valid HTTP response
    Then validate "<capitalcity>" for given country name and statuscode 200
    Examples:
      | countryname                          | capitalcity      |
      | Ukraine                              | Kiev             |
      | Belarus                              | Minsk            |
      | Canada                               | Ottawa           |

   Scenario Outline: Get Capitalcity of given native name
    Given SEND GET RestCountries name service api endpoint with "<CountryNativeName>"
    When receive valid HTTP response
    Then validate "<capitalcity>" for given country name and statuscode 200
     Examples:
       | CountryNativeName | capitalcity |
       | Canada            | Ottawa      |
       | France            | Paris       |



  Scenario Outline: Get Capital cities of given partial name
    Given SEND GET RestCountries name service api endpoint with "<PartialCountryName>"
    When receive valid HTTP response
    Then validate Capital cities for given "<PartialCountryName>" and statuscode 200
    Examples:
      | PartialCountryName |
      | ukr                |


  Scenario Outline: Get Capitalcity of invalid country name
    Given SEND GET RestCountries name service api endpoint with "<CountryName>"
    When receive valid HTTP response
    Then validate error message "<Message>" for given invalid "<CountryName>" and statuscode "<statuscode>"
    Examples:
      | CountryName  | Message   | statuscode |
      | blablabla    | Not Found |        404 |
      | __           | Not Found |        404 |


  Scenario: Verify statuscode for invalid Methods( PUT, DELETE.....)
    Given SEND DELETE RestCountries code service api endpoint with given CountryName
    When receive invalid HTTP response
    Then validate statuscode 405
