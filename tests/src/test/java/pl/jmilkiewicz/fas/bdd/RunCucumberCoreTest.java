package pl.jmilkiewicz.fas.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format = {"pretty", "html:target/cucumber", "json:target/cucumber.json"},
        tags = {"@accepted", "~@ignored"},
        strict = true,
        features = "classpath:features/")
public class RunCucumberCoreTest {
}

