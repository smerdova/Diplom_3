import config.MyConfiguration;
import factory.WebDriverFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.MainPage;

public class ConstructorTest {
    private WebDriver webDriver;
    @Before
    public void setup() throws Exception {
        webDriver = WebDriverFactory.getWebDriver(MyConfiguration.BROWSER_TYPE);
    }

    @Test
    public void bunTabClickSuccess() throws Exception {
        webDriver.get(MainPage.PAGE_URL);
        MainPage mainPage = new MainPage(webDriver);

        if (mainPage.getActiveTabName().equals("Булки")) {
            mainPage.ingredientTabClick();
        }
        mainPage.bunTabClick();

        Assert.assertEquals("Булки", mainPage.getActiveTabName());
    }

    @Test
    public void sauceTabClickSuccess() throws Exception {
        webDriver.get(MainPage.PAGE_URL);
        MainPage mainPage = new MainPage(webDriver);
        mainPage.sauceTabClick();

        Assert.assertEquals("Соусы", mainPage.getActiveTabName());
    }

    @Test
    public void ingredientTabClickSuccess() throws Exception {
        webDriver.get(MainPage.PAGE_URL);
        MainPage mainPage = new MainPage(webDriver);
        mainPage.ingredientTabClick();

        Assert.assertEquals("Начинки", mainPage.getActiveTabName());
    }

    @After
    public void TearDown() {
        webDriver.quit();
    }

}
