// https://github.com/LambdaTest/cucumber-testng-sample/blob/master/src/main/java/MyRunner/TestRunner.java

package runner;

import java.net.URL;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.TestNGCucumberRunner;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

// testng.xml determines which test runner to use if there are multiple

@CucumberOptions(
        features = "src/test/java/features",
        glue = {"steps"},
        plugin = "json:target/cucumber-reports/CucumberTestReport.json")

public class TestNGCucumberRunner1 {  //extends AbstractTestNGCucumberTests //seems redundant if using TestNGCucumberRunner.provideScenarios()
	
    private TestNGCucumberRunner testNGCucumberRunner;
    public static RemoteWebDriver driver;
    
    @BeforeClass(alwaysRun = true)
    public void setUpCucumber() {
    	 testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }
    
    @BeforeMethod(alwaysRun = true)
    @Parameters({ "browser", "version", "platform" })   //from testng.xml
    public void setUpClass(String browser, String version, String platform) throws Exception {

    		String username = System.getenv("LT_USERNAME") == null ? "YOUR LT_USERNAME" : System.getenv("LT_USERNAME"); 
    		String accesskey = System.getenv("LT_ACCESS_KEY") == null ? "YOUR LT_ACCESS_KEY" : System.getenv("LT_ACCESS_KEY"); 

    		DesiredCapabilities capability = new DesiredCapabilities();    		
    		capability.setCapability(CapabilityType.BROWSER_NAME, browser);
    		capability.setCapability(CapabilityType.VERSION,version);
    		capability.setCapability(CapabilityType.PLATFORM, platform);
    		    		
    		capability.setCapability("build", "Cucumber Sample Build");
    		
    		capability.setCapability("network", true);
    		capability.setCapability("video", true);
    		capability.setCapability("console", true);
    		capability.setCapability("visual", true);

    		String gridURL = "https://" + username + ":" + accesskey + "@hub.lambdatest.com/wd/hub";
    		System.out.println(gridURL);
    		driver = new RemoteWebDriver(new URL(gridURL), capability);
    		System.out.println(capability);
    		System.out.println(driver.getSessionId());
}


    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideScenarios();
    }
 
    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        driver.quit();
        testNGCucumberRunner.finish();
    }
}