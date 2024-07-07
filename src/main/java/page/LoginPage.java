package page;

import config.MyConfiguration;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage {

    public static final String PAGE_URL = MyConfiguration.HOME_URL + "/login";

    // Поле "Email"
    @FindBy(xpath = "//label[text()='Email']/../input")
    private WebElement emailInput;

    // Поле "Пароль"
    @FindBy(xpath = "//label[text()='Пароль']/../input")
    private WebElement passwordInput;

    // Кнопка "Войти"
    @FindBy(xpath = "//button[text()='Войти']")
    private WebElement loginButton;

    private final WebDriver webDriver;

    public LoginPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(new AjaxElementLocatorFactory(webDriver,
                MyConfiguration.DEFAULT_WAITING_SEC), this);
    }

    @Step("Вводим email пользователя")
    public void setEmail(String value) {
        emailInput.sendKeys(value);
    }

    @Step("Вводим пароль пользователя")
    public void setPassword(String value) {
        passwordInput.sendKeys(value);
    }

    @Step("Нажимаем кнопку \"Войти\"")
    public void clickLoginButton() { loginButton.click(); }
}
