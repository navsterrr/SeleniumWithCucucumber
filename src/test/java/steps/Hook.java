package steps;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;

//import cucumber.api.PickleStepTestStep;
//import cucumber.api.TestCase;
//import gherkin.pickles.PickleStep;
//import io.cucumber.core.api.Scenario;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import Base.BaseUtil;


/**
 * Created by Karthik on 31/01/2019.
 */

// if outside of package steps, will not execute @Before scenarios below to initialize driver

public class Hook extends BaseUtil{

    // private BaseUtil base;

    // public Hook(BaseUtil base) {
    //     this.base = base;
    // }

    @Before
    public void InitializeTest(Scenario scenario) {
        scenarioDef = features.createNode(scenario.getName());
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        // chromeOptions.addArguments("--headless");
        Driver = new ChromeDriver(chromeOptions);
    }


    @After
    public void TearDownTest(Scenario scenario) {
        if (scenario.isFailed()) {
            //Take screenshot logic goes here
            System.out.println("FAILED: " + scenario.getName());
        }
        System.out.println("Closing the browser : MOCK");
        Driver.quit();
    }

    @BeforeStep
    public void BeforeEveryStep(Scenario scenario) {
        System.out.println("Before every step " + scenario.getId());
    }

    @AfterStep
    public void AfterEveryStep(Scenario scenario) throws NoSuchFieldException, IllegalAccessException {
        //System.out.println("Before every step " + stepTestStep.getId());
    }

}
