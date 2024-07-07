package page;

import config.MyConfiguration;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class RegisterPage {

    public static final String PAGE_URL = MyConfiguration.HOME_URL + "/register";

    private final WebDriver webDriver;

    // Поле "Имя"
    @FindBy(xpath = "//label[text()='Имя']/../input")
    private WebElement nameInput;

    // Поле "Email"
    @FindBy(xpath = "//label[text()='Email']/../input")
    private WebElement emailInput;

    // Поле "Пароль"
    @FindBy(xpath = "//label[text()='Пароль']/../input")
    private WebElement passwordInput;

    // Кнопка "Зарегистрироваться"
    @FindBy(xpath = "//button[text()='Зарегистрироваться']")
    private WebElement registerButton;

    // Сообщение об ошибке
    @FindBy(xpath = "//p[contains(@class, 'input__error')]")
    private WebElement errorText;

    // Гиперссылка "Войти"
    @FindBy(xpath = "//a[@href='/login']")
    private WebElement hrefLogin;

    public RegisterPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(new AjaxElementLocatorFactory(webDriver,
                MyConfiguration.DEFAULT_WAITING_SEC), this);
    }

    @Step("Вводим имя пользователя")
    public void setName(String value) {
        nameInput.sendKeys(value);
    }

    @Step("Вводим email пользователя")
    public void setEmail(String value) {
        emailInput.sendKeys(value);
    }

    @Step("Вводим пароль пользователя")
    public void setPassword(String value) { passwordInput.sendKeys(value); }

    @Step("Переключаем фокус")
    public void loseFocusFromPassword() { passwordInput.sendKeys(Keys.TAB); }

    @Step("Нажимаем кнопку \"Зарегистрироваться\"")
    public void clickRegisterButton() { registerButton.click(); }

    @Step("Читаем текст ошибки")
    public String getErrorText() { return errorText.getText(); }

    @Step("Переходим по ссылке \"Войти\"")
    public void clickLoginHref() { hrefLogin.click(); }
}
