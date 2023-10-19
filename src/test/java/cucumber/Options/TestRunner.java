package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features={"src/test/java/features/PlaceValidations.feature","src/test/java/features/LibraryApi.feature"},
plugin="json:target/jsonReports/cucumber-report.json",glue= {"stepDefinations"},monochrome=true)
public class TestRunner {

}
