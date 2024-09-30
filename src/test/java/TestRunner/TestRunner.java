package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "StepDefs",
        plugin = {"html:target/cucumber-reports/cucumber-html-report.html"},
        tags= "@regression",
        monochrome = true

)
public class TestRunner {

}