package rest.countries;


import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources",
		monochrome = true,
				glue= {"rest.countries.stepdefinitions"},
						plugin = { "pretty",
								"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html",
								"html:target/cucumber-reports","json:target/cucumber-reports/Cucumber.json",
								"junit:target/cucumber-reports/Cucumber.xml", }
		)
public class TestRunner {
	 @AfterClass
	 public static void writeExtentReport() {
	 Reporter.loadXMLConfig("configs/extent-config.xml");
	 }
}
