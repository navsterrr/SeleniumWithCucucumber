package steps;

import Base.BaseUtil;
import com.aventstack.extentreports.GherkinKeyword;

import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import pages.LoginPage;

import java.util.List;
import java.util.Map;

/**
 * Created by Karthik on 31/01/2019.
 */
public class LoginStep extends BaseUtil{

    // private  BaseUtil base;

    // public LoginStep(BaseUtil base) {
    //     // this.base = base;
    // }

    @DataTableType(replaceWithEmptyString = "[blank]")
    public User convert(Map<String, String> entry){
        return new User(
                entry.get("username"),
                entry.get("password")
                    // .concat("$$$$$")
        );
    }


    @Then("^I should see the userform page$")
    public void iShouldSeeTheUserformPage() throws Throwable {
        scenarioDef.createNode(new GherkinKeyword("Then"), "I should see the userform page");

        Assert.assertEquals("Its not displayed", Driver.findElement(By.id("Initial")).isDisplayed(), true);
    }

    // LoginPage.load() already navigates to Login URL
    // @Given("^I navigate to the login page$")
    // public void iNavigateToTheLoginPage() throws Throwable {
    //     scenarioDef.createNode(new GherkinKeyword("Given"), "I navigate to the login page");
    //     System.out.println("Navigate Login Page");
    //     // base.Driver.navigate().to("http://www.executeautomation.com/demosite/Login.html");  //404
    //     Driver.navigate().to("https://www.selenium.dev/selenium/web/javascriptEnhancedForm.html");
    // }


    @And("^I click login button$")
    public void iClickLoginButton() throws Throwable {
        scenarioDef.createNode(new GherkinKeyword("And"), "I click login button");
        LoginPage page = new LoginPage(Driver);
        page.ClickLogin();
    }


    @Given("^I enter the following for Login$")
    public void iEnterTheFollowingForLogin(List<User> table) throws Throwable {
        scenarioDef.createNode(new GherkinKeyword("And"), "I enter the following for login");
        //Create an ArrayList
        //List<User> users =  new ArrayList<User>();
        //Store all the users
        //List<User> users = table.asList(User.class);

        LoginPage page = new LoginPage(Driver).get();   //calls LoginPage.load() & .isLoaded() to navigate to Login Page url 

        page.Login(table.get(0).username, table.get(0).password);

        //page.Login(users.get(2), users.get(3));

    }

    @And("^I enter ([^\"]*) and ([^\"]*)$")
    public void iEnterUsernameAndPassword(String userName, String password) throws Throwable {
        scenarioDef.createNode(new GherkinKeyword("And"), "I enter username and password");
        System.out.println("UserName is : " + userName);
        System.out.println("Password is : " + password);
    }

    @Then("^I should see the userform page wrongly$")
    public void iShouldSeeTheUserformPageWrongly() throws Throwable {
        scenarioDef.createNode(new GherkinKeyword("Then"), "I should see  the useform page wrongly");
        //Assert.assertEquals("Its not displayed", Driver.findElement(By.id("sdfgdsfsd")).isDisplayed(), true);
    }


    private class User {
        private String username;
        private String password;

        public User(String username, String password) {
            this.username= username;
            this.password = password;
        }
    }

}
