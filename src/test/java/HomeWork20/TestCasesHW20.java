package HomeWork20;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TestCasesHW20 extends BaseTest{

//    @Test
//    public void threadSleep() {
//
//        webDriver.get("");
//        findElement(webDriver, By.xpath(""));
//
//        waitForElementClickable(webDriver, By.xpath("")).click();

//        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
//        wait
//                .until(ExpectedConditions.elementToBeClickable(By.xpath(""))).click();

//        Wait<WebDriver> wait = new FluentWait<>(webDriver)
//                .withTimeout(Duration.ofSeconds(10)).
//                pollingEvery(Duration.ofSeconds(3));
//
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(""))).click();
//    }

    //1. Написать 2 теста, которые делали на уроке
    @Test
    public void testScrolling() {
        webDriver.get("https://www.gurock.com/testrail/");
        WebElement we = findElement(webDriver, By.xpath("//a[contains(., 'Legal')]"));
        scrollToElement(webDriver, we);
        we.click();

        List<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(1));
        findElement(webDriver, By.xpath("//a[@id='ui-id-3']")).click();
    }

    //2. Для ресурса https://www.gurock.com/testrail/ написать 4 теста для ссылок: About Gurock, Jobs, Contact, Compliance
    @Test
    public void TestAboutGurock() {
        webDriver.get("https://www.gurock.com/testrail/");
        WebElement aboutGurock = findElement(webDriver, By.xpath("//li[@id='menu-item-704']/a[contains(., 'About Gurock')]"));
        scrollToElement(webDriver, aboutGurock);
        aboutGurock.click();
        String checkText = findElement(webDriver, By.xpath("//div[@class='et_pb_text_inner']/h1[contains(., 'Our Story')]")).getText();

        Assert.assertEquals(checkText, "Our Story");
    }

    @Test
    public void TestJobs() {
        webDriver.get("https://www.gurock.com/testrail/");
        WebElement jobs = findElement(webDriver, By.xpath("//li[@id='menu-item-705']/a[contains(., 'Jobs')]"));
        scrollToElement(webDriver, jobs);
        jobs.click();

        boolean isButtonDisplayed = findElement(webDriver, By.xpath("//a[@class='btn btn-medium btn-outline-blue']")).isDisplayed();
        Assert.assertTrue(isButtonDisplayed, "There is no button 'Browse open positions' on the page");
    }

    @Test
    public void TestContact() {
        webDriver.get("https://www.gurock.com/testrail/");
        WebElement contact = findElement(webDriver, By.xpath("//li[@id='menu-item-706']/a[contains(., 'Contact')]"));
        scrollToElement(webDriver, contact);
        contact.click();
        String checkText = findElement(webDriver, By.xpath("//div[@class='et_pb_text_inner']/h1[contains(., 'Contact the TestRail Team')]")).getText();

        Assert.assertEquals(checkText, "Contact the TestRail Team");
    }

    @Test
    public void TestCompliance() {
        webDriver.get("https://www.gurock.com/testrail/");
        WebElement compliance = findElement(webDriver, By.xpath("//li[@id='menu-item-9627']/a[contains(., 'Compliance')]"));
        scrollToElement(webDriver, compliance);
        compliance.click();

        boolean isTextDisplayed = findElement(webDriver, By.xpath("//h1/span[contains(text(), 'TestRail SOC 2 Compliance & Certification')]")).isDisplayed();
        Assert.assertTrue(isTextDisplayed, "There is no text 'TestRail SOC 2 Compliance & Certification' on the page");
    }

    //3. Для ссылки Legal, перейти на следующее окно, кликнуть на одну из внутренних таб,
    // проверить что отобразился необходимый контент, вернуться на предыдущее окно и кликнуть на кнопку Subscribe
    @Test
    public void TestLegal() {
        webDriver.get("https://www.gurock.com/testrail/");
        WebElement legal = findElement(webDriver, By.xpath("//li[@id='menu-item-2896']/a[contains(., 'Legal')]"));
        scrollToElement(webDriver, legal);
        legal.click();

        List<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(1));
        findElement(webDriver, By.xpath("//a[@id='ui-id-4']")).click();
        String checkText = findElement(webDriver, By.xpath("//a[contains(., 'Trademark usage guidelines and abuse policy')]")).getText();
        Assert.assertEquals(checkText, "Trademark usage guidelines and abuse policy");
        webDriver.switchTo().window(tabs.get(0));

        By subscribeButton = By.xpath("//div[@class='col-6 col-sm-6 col-md-4 col-lg-3 gk-footer-menu-item-first']/a[@class='btn btn-medium btn-green']");
        waitForElementClickable(webDriver,subscribeButton);
        findElement(webDriver, By.xpath("//div[@class='col-6 col-sm-6 col-md-4 col-lg-3 gk-footer-menu-item-first']/a[@class='btn btn-medium btn-green']")).click();
        Assert.assertTrue(webDriver.getCurrentUrl().contains("newsletter"));
    }
}
