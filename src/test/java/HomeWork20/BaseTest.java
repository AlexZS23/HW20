package HomeWork20;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseTest extends WebHelpers{
    protected WebHelpers wb = new WebHelpers();
    protected static WebDriver webDriver;

    @BeforeClass
    public void initWebDriver() {
        String browser = "chrome";

        switch (browser) {
            case "chrome":
                webDriver = DriverInit.getWebDriver(DriverInit.Browser.CHROME);
                break;
            case "ff":
                webDriver = DriverInit.getWebDriver(DriverInit.Browser.FF);
                break;
            default:
                System.out.println("You need to initialize browser");
        }
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @AfterClass
    public void closeBrowser() {
        webDriver.quit();
    }

}
