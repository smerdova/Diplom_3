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

public class RegisterUserTest {
    private Faker faker;
    private String name;
    private String email;
    private String password;
    private String accessToken;
    private WebDriver webDriver;
    @Before
    public void setup() throws Exception {
        this.faker = new Faker();
        this.name = faker.name().name();
        this.email = faker.internet().emailAddress();
        this.password = faker.internet().password(7, 10);

        this.webDriver = WebDriverFactory.getWebDriver(MyConfiguration.BROWSER_TYPE);
    }

    @Test
    public void registerUserSuccess() throws Exception {
        webDriver.get(RegisterPage.PAGE_URL);
        RegisterPage registerPagePage = new RegisterPage(webDriver);
        registerPagePage.setName(name);
        registerPagePage.setEmail(email);
        registerPagePage.setPassword(password);
        registerPagePage.clickRegisterButton();

        // Логин, для проверки и последующего удаления
        accessToken = AuthSteps.login(new AuthRequest(email, password, name))
                .body("success", equalTo(true))
                .extract().body().path("accessToken");
    }

    @Test
    public void registerUserTooShortPasswordFails() throws Exception {
        webDriver.get(RegisterPage.PAGE_URL);
        RegisterPage registerPagePage = new RegisterPage(webDriver);
        registerPagePage.setName(name);
        registerPagePage.setEmail(email);
        registerPagePage.setPassword(faker.internet().password(1, 6));
        registerPagePage.loseFocusFromPassword();

        Assert.assertEquals("Некорректный пароль", registerPagePage.getErrorText());
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
