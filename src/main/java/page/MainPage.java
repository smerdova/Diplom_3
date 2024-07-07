package page;

import config.MyConfiguration;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class MainPage {

    public static final String PAGE_URL = MyConfiguration.HOME_URL;
    private final WebDriver webDriver;

    // Кнопка "Войти в аккаунт"
    @FindBy(xpath = "//button[text()='Войти в аккаунт']")
    private WebElement loginButton;

    // Кнопка "Оформить заказ"
    @FindBy(xpath = "//button[text()='Оформить заказ']")
    private WebElement orderButton;

    // Кнопка "Личный кабинет"
    @FindBy(xpath = "//a[@href='/account']")
    private WebElement accountButton;

    // Заголовок "Соберите бургер"
    @FindBy(xpath = "//h1[text()='Соберите бургер']")
    private WebElement ConstructorHeader;

    // Раздел "Булки"
    @FindBy(xpath = "//span[text()='Булки']/..")
    private WebElement bunTab;

    // Раздел "Соусы"
    @FindBy(xpath = "//span[text()='Соусы']/..")
    private WebElement sauceTab;

    // Раздел "Начинки"
    @FindBy(xpath = "//span[text()='Начинки']/..")
    private WebElement ingredientTab;

    public MainPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(new AjaxElementLocatorFactory(webDriver,
                MyConfiguration.DEFAULT_WAITING_SEC), this);
    }

    @Step("Нажимаем кнопку \"Войти в аккаунт\"")
    public void clickLoginButton() { loginButton.click(); }

    @Step("Нажимаем кнопку \"Личный кабинет\"")
    public void clickAccountButton() { accountButton.click(); }

    @Step("Ищем кнопку \"Оформить заказ\"")
    public WebElement getOrderButtonLocator() { return orderButton; }

    @Step("Ищем заголовок \"Соберите бургер\"")
    public WebElement getConstructorHeader() { return ConstructorHeader; }

    @Step("Ищем кнопку \"Войти в аккаунт\"")
    public WebElement getLoginButton() { return loginButton; }

    @Step("Выбираем раздел \"Булки\"")
    public void bunTabClick() { bunTab.click(); }

    @Step("Выбираем раздел \"Соусы\"")
    public void sauceTabClick() { sauceTab.click(); }

    @Step("Выбираем раздел \"Начинки\"")
    public void ingredientTabClick() { ingredientTab.click(); }

    @Step("Получаем название активного раздела")
    public String getActiveTabName() {
        WebElement activeTab = webDriver.findElement(
                By.xpath("//div[contains(@class, 'tab_tab_type_current')]/span"));

        return activeTab.getText();
    }
}
