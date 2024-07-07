package config;

import factory.WebDriverFactory;

public abstract class MyConfiguration {
    public static final String HOME_URL = "https://stellarburgers.nomoreparties.site";

    public static final String BASE_API_URL = "https://stellarburgers.nomoreparties.site";

    public static final int DEFAULT_WAITING_SEC = 15;

    public static final WebDriverFactory.BrowserType BROWSER_TYPE = WebDriverFactory.BrowserType.CHROME;

    public static final String YANDEX_DRIVER_PATH = "c:\\WebDriver\\bin\\yandexdriver.exe";
}
