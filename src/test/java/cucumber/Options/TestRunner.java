package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features={"src/test/java/features"},
plugin="html:target/htmlReports/cucumber-report.html",glue= {"stepDefinations"},monochrome=true)
public class TestRunner {

}
