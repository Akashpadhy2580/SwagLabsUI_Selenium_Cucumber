package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/login.feature",
        glue = "StepDefs",
        plugin = {"html:target/cucumber-reports/cucumber-html-report.html"},
        monochrome = true

)
public class TestRunner {

}