import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "11");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/konstantin/Desktop/JavaAutoTests/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    // Тест для проверки того, что у элемента на странице есть определенный текст
    @Test
    public void checkElementHasText() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find field 'Search Wikipedia' input",
                5
        );

        WebElement element = waitForElementPresent(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find 'Search Wikipedia' text",
                15
        );

        assertElementHasText(
                element,
                "Search Wikipedia",
                "No text Search Wikipedia"
        );

    }

    // Тест для проверки того, что при запросе в поиске отображается несколько искомых статей, потом проверяем отмену поиска
    @Test
    public void checkSearchCancel() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Appium",
                "Cannot find search input",
                5
        );

        WebElement element1 = waitForElementPresent(
                By.xpath("//android.view.ViewGroup[@index='0']/android.widget.TextView"),
                "Cannot find 'Appium' text",
                15
        );
        WebElement element2 = waitForElementPresent(
                By.xpath("//android.view.ViewGroup[@index='1']/android.widget.TextView"),
                "Cannot find 'Appius' text",
                15
        );

        assertElementHasText(
                element1,
                "Appium",
                "No text 'Appium'"
        );

        assertElementHasText(
                element2,
                "Appius Claudius Caecus",
                "No text 'AppImage'"
        );

        waitForElementAndClick(
                By.className("android.widget.ImageButton"),
                "Cannot find 'Back button' to cancel search",
                5
        );

        waitForElementNotPresent(
                By.className("android.widget.ImageButton"),
                "Back button is still on a page",
                5
        );

    }

    // Тест для проверки названия каждой статьи на странице
    @Test
    public void checkArticlesContainText() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "java",
                "Cannot find search input",
                5
        );

        WebElement element0 = waitForElementPresent(
                By.xpath("//android.view.ViewGroup[@index='0']/android.widget.TextView"),
                "Cannot find 'java' text",
                15
        );
        WebElement element1 = waitForElementPresent(
                By.xpath("//android.view.ViewGroup[@index='1']/android.widget.TextView"),
                "Cannot find 'java' text",
                15
        );
        WebElement element2 = waitForElementPresent(
                By.xpath("//android.view.ViewGroup[@index='0']/android.widget.TextView"),
                "Cannot find 'java' text",
                15
        );
        WebElement element3 = waitForElementPresent(
                By.xpath("//android.view.ViewGroup[@index='1']/android.widget.TextView"),
                "Cannot find 'java' text",
                15
        );
        WebElement element4 = waitForElementPresent(
                By.xpath("//android.view.ViewGroup[@index='0']/android.widget.TextView"),
                "Cannot find 'java' text",
                15
        );
        WebElement element5 = waitForElementPresent(
                By.xpath("//android.view.ViewGroup[@index='1']/android.widget.TextView"),
                "Cannot find 'java' text",
                15
        );
        WebElement element6 = waitForElementPresent(
                By.xpath("//android.view.ViewGroup[@index='0']/android.widget.TextView"),
                "Cannot find 'java' text",
                15
        );
        WebElement element7 = waitForElementPresent(
                By.xpath("//android.view.ViewGroup[@index='1']/android.widget.TextView"),
                "Cannot find 'java' text",
                15
        );

        assertElementHasText(
                element0,
                "java",
                "No text 'java'"
        );

        assertElementHasText(
                element1,
                "java",
                "No text 'java'"
        );
        assertElementHasText(
                element2,
                "java",
                "No text 'java'"
        );

        assertElementHasText(
                element3,
                "java",
                "No text 'java'"
        );
        assertElementHasText(
                element4,
                "java",
                "No text 'java'"
        );

        assertElementHasText(
                element5,
                "java",
                "No text 'java'"
        );
        assertElementHasText(
                element6,
                "java",
                "No text 'java'"
        );

        assertElementHasText(
                element7,
                "java",
                "No text 'java'"
        );
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until (
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element =waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    private void assertElementHasText(WebElement element, String expected_text, String error_message) {
        String actual_text = element.getAttribute("text");
        if (actual_text.toLowerCase().contains(expected_text.toLowerCase())) {
            System.out.println("Test passed! Element text is equal to expected text");
        }
        else {
            Assert.fail("Test failed! Element text is NOT equal to expected text");
        }
    }
}
