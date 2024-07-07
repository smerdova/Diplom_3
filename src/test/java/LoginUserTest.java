import com.github.javafaker.Faker;
import config.MyConfiguration;
import factory.WebDriverFactory;
import model.AuthRequest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.ForgotPasswordPage;
import page.LoginPage;
import page.MainPage;
import page.RegisterPage;
import steps.AuthSteps;

import static org.hamcrest.CoreMatchers.equalTo;

public class LoginUserTest {
    private AuthRequest authRequest;
    private String accessToken;
    private WebDriver webDriver;
    @Before
    public void setup() throws Exception {
        Faker faker = new Faker();
        this.authRequest = new AuthRequest(
                faker.internet().emailAddress(),
                faker.name().name(),
                faker.internet().password(7, 10)
        );

        accessToken = AuthSteps.register(authRequest)
                .statusCode(200)
                .body("success", equalTo(true))
                .extract().body().path("accessToken");


        this.webDriver = WebDriverFactory.getWebDriver(MyConfiguration.BROWSER_TYPE);
    }

    @Test
    public void loginUserFromMainToLoginButtonSuccess() throws Exception {
        webDriver.get(MainPage.PAGE_URL);
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickLoginButton();
        login();

        MainPage loggedInMainPage = new MainPage(webDriver);
        Assert.assertNotNull(loggedInMainPage.getOrderButtonLocator());
    }

    @Test
    public void loginUserFromMainToAccountButtonSuccess() throws Exception {
        webDriver.get(MainPage.PAGE_URL);
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickAccountButton();
        login();

        MainPage loggedInMainPage = new MainPage(webDriver);
        Assert.assertNotNull(loggedInMainPage.getOrderButtonLocator());
    }

    @Test
    public void loginUserFromRegisterPageSuccess() throws Exception {
        webDriver.get(RegisterPage.PAGE_URL);
        RegisterPage registerPage = new RegisterPage(webDriver);
        registerPage.clickLoginHref();
        login();

        MainPage loggedInMainPage = new MainPage(webDriver);
        Assert.assertNotNull(loggedInMainPage.getOrderButtonLocator());
    }

    @Test
    public void loginUserFromForgotPasswordPageSuccess() throws Exception {
        webDriver.get(ForgotPasswordPage.PAGE_URL);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(webDriver);
        forgotPasswordPage.clickLoginHref();
        login();

        MainPage loggedInMainPage = new MainPage(webDriver);
        Assert.assertNotNull(loggedInMainPage.getOrderButtonLocator());
    }

    private void login() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.setEmail(authRequest.getEmail());
        loginPage.setPassword(authRequest.getPassword());
        loginPage.clickLoginButton();
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
