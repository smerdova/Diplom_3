import com.github.javafaker.Faker;
import config.MyConfiguration;
import factory.WebDriverFactory;
import model.AuthRequest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.*;
import steps.AuthSteps;

import static org.hamcrest.CoreMatchers.equalTo;

public class AccountTest {
    private AuthRequest authRequest;
    private String accessToken;
    private WebDriver webDriver;
    @Before
    public void setup() throws Exception {
        Faker faker = new Faker();
        authRequest = new AuthRequest(
                faker.internet().emailAddress(),
                faker.name().name(),
                faker.internet().password(7, 10)
        );

        accessToken = AuthSteps.register(authRequest)
                .statusCode(200)
                .body("success", equalTo(true))
                .extract().body().path("accessToken");


        webDriver = WebDriverFactory.getWebDriver(MyConfiguration.BROWSER_TYPE);

        webDriver.get(LoginPage.PAGE_URL);
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.setEmail(authRequest.getEmail());
        loginPage.setPassword(authRequest.getPassword());
        loginPage.clickLoginButton();
    }

    @Test
    public void enterToProfileByButtonClickSuccess() throws Exception {
        MainPage loggedInMainPage = new MainPage(webDriver);
        loggedInMainPage.clickAccountButton();

        AccountProfilePage accountProfilePage = new AccountProfilePage(webDriver);
        Assert.assertNotNull(accountProfilePage.getExitButton());
    }

    @Test
    public void goToConstructorFromAccountPageSuccess() throws Exception {
        MainPage loggedInMainPage = new MainPage(webDriver);
        loggedInMainPage.clickAccountButton();

        AccountProfilePage accountProfilePage = new AccountProfilePage(webDriver);
        accountProfilePage.clickConstructorHref();

        MainPage mainPage = new MainPage(webDriver);
        Assert.assertNotNull(mainPage.getConstructorHeader());
    }

    @Test
    public void logoutSuccess() throws Exception {
        MainPage loggedInMainPage = new MainPage(webDriver);
        loggedInMainPage.clickAccountButton();

        AccountProfilePage accountProfilePage = new AccountProfilePage(webDriver);
        accountProfilePage.clickExitButton();

        MainPage mainPage = new MainPage(webDriver);
        Assert.assertNotNull(mainPage.getLoginButton());
    }

    @After
    public void TearDown() {
        webDriver.quit();

        if (accessToken != null) {
            AuthSteps.delete(accessToken)
                    .statusCode(202);
        }
    }

}
