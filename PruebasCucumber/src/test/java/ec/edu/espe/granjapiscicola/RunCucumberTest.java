package ec.edu.espe.granjapiscicola;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"ec.edu.espe.granjapiscicola"},
    plugin = {"pretty", "html:target/cucumber-html-report", "json:target/cucumber-report/cucumber.json"}

)
public class RunCucumberTest {

}