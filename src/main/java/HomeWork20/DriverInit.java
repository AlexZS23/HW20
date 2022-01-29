package HomeWork20;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v85.browser.Browser;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverInit {
    private static WebDriver webDriver;

    private void setWebDriver(Browser browserName) {
        switch (browserName) {
            case CHROME:
                webDriver = WebDriverManager.chromedriver().create(); //один подход
                break;
            case FF:
                WebDriverManager.firefoxdriver().setup(); //второй подход
                webDriver = new FirefoxDriver();
                break;
        }
    }

    private DriverInit(Browser browser) {
        setWebDriver(browser);
    }

    public static WebDriver getWebDriver(Browser browser) {
        if (webDriver == null) {
            new DriverInit(browser);
        }
        return webDriver;
    }

    public enum Browser {
        CHROME,
        FF
    }
}


