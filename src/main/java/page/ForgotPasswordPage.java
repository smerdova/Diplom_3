package page;

import config.MyConfiguration;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ForgotPasswordPage {

    public static final String PAGE_URL = MyConfiguration.HOME_URL + "/forgot-password";

    private final WebDriver webDriver;

    // Гиперссылка "Войти"
    @FindBy(xpath = "//a[@href='/login']")
    private WebElement hrefLogin;

    public ForgotPasswordPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(new AjaxElementLocatorFactory(webDriver,
                MyConfiguration.DEFAULT_WAITING_SEC), this);
    }

    @Step("Переходим по ссылке \"Войти\"")
    public void clickLoginHref() { hrefLogin.click(); }
}
