// https://github.com/paras301/testng-with-cucumber/blob/main/src/test/java/com/company/runner/TestRunner.java

package runner;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

// testng.xml determines which test runner to use if there are multiple

@Slf4j
@CucumberOptions(
		features = {"src/test/java/features"},
		glue = {"steps"},
		monochrome = false,
		dryRun = false,
		plugin = {
				"json:build/cucumber-reports/cucumber.json",
				"rerun:build/cucumber-reports/rerun.txt"
		})
public class TestNGCucumberRunner2 {

	private TestNGCucumberRunner testNGCucumberRunner;
	public static WebDriver webdriver;	

	@BeforeSuite(alwaysRun = true)
	@Parameters({"platform"})	//from testng.xml
	public void setUpCucumber(String platform) throws Exception {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());

		if (platform.equalsIgnoreCase("Windows")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/src/main/resources/Drivers/chromedriver_windows_100.0.4896.60.exe");
			ChromeOptions options = new ChromeOptions();
			//options.setHeadless(true);
			options.addArguments("--disable-notifications");
			webdriver = new ChromeDriver(options);

		} else if (platform.equalsIgnoreCase("Linux")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/src/main/resources/Drivers/chromedriver_linux64_100.0.4896.60");
			ChromeOptions options = new ChromeOptions();
			// options.setHeadless(true);
			options.addArguments("--disable-notifications");
			webdriver = new ChromeDriver(options);
		}

		log.info("Chrome Driver Initialized.");
	}

	// dataProvider = "scenarios" => uses scenarios() to data-drive tests
	@Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
	public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
		testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
	}

	@DataProvider
	public Object[][] scenarios() {
		return testNGCucumberRunner.provideScenarios();
	}

	@AfterSuite(alwaysRun = true)
	public void tearDownClass() throws Exception {
		webdriver.quit();
		testNGCucumberRunner.finish();
	}
}