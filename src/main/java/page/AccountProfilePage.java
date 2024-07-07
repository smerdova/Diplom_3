package page;

import config.MyConfiguration;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class AccountProfilePage {

    public static final String PAGE_URL = MyConfiguration.HOME_URL + "/account/profile";

    private final WebDriver webDriver;

    // Кнопка "Выход"
    @FindBy(xpath = "//button[text()='Выход']")
    private WebElement exitButton;

    // Ссылка на конструктор
    @FindBy(xpath = "//p[text()='Конструктор']")
    private WebElement constructorHref;

    public AccountProfilePage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(new AjaxElementLocatorFactory(webDriver,
                MyConfiguration.DEFAULT_WAITING_SEC), this);
    }

    @Step("Поиск кнопки \"Выход\"")
    public WebElement getExitButton() { return exitButton; }

    @Step("Нажатие кнопки \"Выход\"")
    public void clickExitButton() { exitButton.click(); }

    @Step("Переход в Конструктор")
    public void clickConstructorHref() { constructorHref.click(); }

}
