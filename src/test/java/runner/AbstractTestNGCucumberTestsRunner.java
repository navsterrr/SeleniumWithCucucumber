package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * Created by Karthik on 21/09/2019.
 */

// testng.xml determines which test runner to use if there are multiple

@CucumberOptions(features = {"src/test/java/features"} , 
        plugin = {"json:target/cucumber.json", "pretty"},
        glue = "steps")
public class AbstractTestNGCucumberTestsRunner extends AbstractTestNGCucumberTests {   //Runs each cucumber scenario found in the features as separated test.

    // Webdriver NOT initialized here, it is initialized in steps.Hook. Consider refactoring to initialize it here

    @Override
    @DataProvider
    //@DataProvider (parallel = true) -- For parallel execution support (which is not going to work for our code)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
