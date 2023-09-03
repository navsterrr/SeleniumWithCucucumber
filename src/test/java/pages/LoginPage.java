package pages;

import static org.junit.Assert.assertTrue;

import java.sql.Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

/**
 * Created by Karthik on 21/09/2019.
 */
public class LoginPage extends LoadableComponent<LoginPage> {

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;

        // This call sets the WebElement fields
        PageFactory.initElements(driver, this);
    }

    // By default the PageFactory will locate elements with the same "name" or "id"
    // as the field. Since the "username" element has a @name attribute of
    // "username"
    // we don't need any additional annotations.
    // @FindBy(how = How.ID, using = "username")
    // @FindBy(name = "username") //redundant if element name is named after the ID
    // or NAME attribute
    public WebElement username;

    // @FindBy(how = How.ID, using = "password")
    public WebElement password;

    // @FindBy(how = How.CSS, using = "input[type=submit]")
    @FindBy(css = "input[type=submit]")
    public WebElement btnSubmit;

    public void Login(String txtUsername, String txtPassword) {

        username.clear();
        username.sendKeys(txtUsername);
        password.sendKeys(txtPassword);
    }

    public void ClickLogin() {
        btnSubmit.submit();
    }

    @Override
    protected void load() {
        driver.navigate().to("https://www.selenium.dev/selenium/web/javascriptEnhancedForm.html");
    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        assertTrue("Now on the Login page: " + url, url.endsWith("/javascriptEnhancedForm.html"));
    }

}
