package factory;

import config.MyConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class WebDriverFactory {
    public static WebDriver getWebDriver(BrowserType browserType) throws Exception {
        switch (browserType) {
            case  FIREFOX:
                return new FirefoxDriver();

            case  CHROME:
                return new ChromeDriver();

            case  YANDEX:
                System.setProperty("webdriver.chrome.driver", MyConfiguration.YANDEX_DRIVER_PATH);
                return new ChromeDriver();

            default:
                throw new Exception("Неизвестный браузер");
        }
    }

    public enum BrowserType {
        CHROME,
        FIREFOX,
        YANDEX
    }
}
