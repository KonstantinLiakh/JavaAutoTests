import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.List;

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

    @Test
    public void testSwipeArticle() {
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

        driver.hideKeyboard();
        waitForElementAndClick(
                By.xpath("//android.view.ViewGroup[@index='0']/android.widget.TextView"),
                "Cannot find 'Appium' text",
                15
        );

        swipeUpToFindElement(
                By.xpath("//*[contains(@text, 'View article in browser')]"),
                "Cannot find the end of the article",
                20
        );
    }

    @Test
    public void saveFirstArticleToMyList(){
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip button' input",
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
        waitForElementAndClick(
                By.xpath("//android.view.ViewGroup[@index='2']/android.widget.TextView"),
                "Cannot find 'java' text",
                15
        );
        waitForElementAndClick(
                By.xpath("//android.widget.TextView[contains(@text, 'Save')]"),
                "cannot find find button for 'Save' the article",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.Button[contains(@text, 'ADD TO LIST')]"),
                "cannot find find button for article to 'ADD TO LIST'",
                5
        );
        String name_of_my_folder = "My new list";

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_my_folder,
                "Cannot type text for 'My new list' saved items",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.Button[contains(@text, 'OK')]"),
                "Cannot press 'OK' button to save articel to my list",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[contains(@content-desc, 'Navigate up')]"),
                "Cannot close article",
                5
        );
        waitForElementAndClick(
                By.className("android.widget.ImageButton"),
                "Cannot find 'Back button' to cancel search",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[contains(@content-desc, 'Saved')]"),
                "Cannot get to my 'Saved' articles",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.TextView[contains(@text, '" + name_of_my_folder +"')]"),
                "Cannot get to my 'Saved' articles list",
                5
        );

        swipeElementToLeft(
                By.xpath("//android.widget.TextView[contains(@text, 'Java (programming language)')]"),
                "Cannot find saved article"
        );
        waitForElementNotPresent(
                By.xpath("//android.widget.TextView[contains(@text, 'Java (programming language)')]"),
                "Cannot delete saved article",
                5
        );

    }

    @Test
    public void TestAmountOfNotEmptySearch(){
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip button' input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_line = "Linkin Park Discography";

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find 'Linkin Park Discography",
                5
        );

        String search_result_locator = "//android.view.ViewGroup[@index='0']/android.widget.TextView[@text='Linkin Park discography' and @index='0']";

        waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything by the request" + search_line,
                15
        );

        int amount_of_search_results = getAmountOfElements(
                By.xpath(search_result_locator)
        );
        Assert.assertTrue(
                "We found a few results",
                amount_of_search_results > 0
        );

    }

    @Test
    public void amountOfEmptySearch() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip button' input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_line = "liwdqwdawdasdsadasd";

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find 'liwdqwdawdasdsadasd",
                5
        );

        String search_result_locator = "//android.view.ViewGroup[@index='0']/android.widget.TextView[@text='Linkin Park discography' and @index='0']";
        String empty_result_label = "//*[contains(@text, 'No results')]";

        waitForElementPresent(
                By.xpath(empty_result_label),
                "Cannot find empty result label by the request " + search_line,
                15
        );

        assertElementNotPresent(
                By.xpath(search_result_locator),
                "We found some results by request " + search_line
        );

    }

    @Test
    public void testChangeOrientationScreenInSearchResults(){
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip button' input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_line = "java";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find search input",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.TextView[contains(@text, 'Java (programming language)')]"),
                "Cannot find 'Java (programming language)' text by " + search_line,
                15
        );

        String title_before_rotation = waitForElementAndGetAttribute(
                By.xpath("//android.view.View[contains(@text, 'Java (programming language)')]"),
                "text",
                "Cannot find the title of article",
                15
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = waitForElementAndGetAttribute(
                By.xpath("//android.view.View[contains(@text, 'Java (programming language)')]"),
                "text",
                "Cannot find the title of article",
                15
        );

        Assert.assertEquals(
                "Article title has been changed after rotation",
                title_before_rotation,
                title_after_rotation
        );

        driver.rotate(ScreenOrientation.PORTRAIT);

        String title_after_second_rotation = waitForElementAndGetAttribute(
                By.xpath("//android.view.View[contains(@text, 'Java (programming language)')]"),
                "text",
                "Cannot find the title of article",
                15
        );

        Assert.assertEquals(
                "Article title has been changed after rotation",
                title_before_rotation,
                title_after_second_rotation
        );



    }

    @Test
    public void checkArticleTextAfterBackground(){
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip button' input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_line = "java";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find search input",
                5
        );

        waitForElementPresent(
                By.xpath("//android.view.ViewGroup[@index='2']/android.widget.TextView[@text='Java (programming language)']"),
                "Cannot find article in search results",
                15
        );

        driver.runAppInBackground(Duration.ofSeconds(2));

        waitForElementPresent(
                By.xpath("//android.view.ViewGroup[@index='2']/android.widget.TextView[@text='Java (programming language)']"),
                "Cannot find article in search results after returning from background",
                15
        );
    }

    @Test
    public void saveTwoArticlesAndDeleteOneOfIt(){
        //methods to remember 1st article title, save it and go back to articles list
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip button' input",
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
        waitForElementAndClick(
                By.xpath("//android.view.ViewGroup[@index='1']/android.widget.TextView"),
                "Cannot find 'java' text",
                15
        );

        String title_before_saving = waitForElementAndGetAttribute(
                By.xpath("//android.view.View[contains(@text, 'JavaScript')]"),
                "text",
                "Cannot find the title of 1st article",
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[contains(@text, 'Save')]"),
                "Cannot find find button to 'Save' 1st article",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.Button[contains(@text, 'ADD TO LIST')]"),
                "Cannot find find button for 1st article to 'ADD TO LIST'",
                5
        );

        String name_of_my_folder = "List_for_test";

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_my_folder,
                "Cannot type text 'List_for_test' for new saved list",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.Button[contains(@text, 'OK')]"),
                "Cannot press 'OK' button to save 1st article to my list",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[contains(@content-desc, 'Navigate up')]"),
                "Cannot close 1st article",
                5
        );

        //methods to save 2nd article
        waitForElementAndClick(
                By.xpath("//android.view.ViewGroup[@index='2']/android.widget.TextView"),
                "Cannot find 'java' text",
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[contains(@text, 'Save')]"),
                "Cannot find find button 'Save' to save 2nd article",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.Button[contains(@text, 'ADD TO LIST')]"),
                "Cannot find find button for 2nd article to 'ADD TO LIST'",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[contains(@text, 'List')]"),
                "Cannot press 'OK' button to save 2nd article to my list",
                5
        );
        //Go to saved list, delete 2nd article and keep 1st article
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[contains(@content-desc, 'Navigate up')]"),
                "Cannot close 2nd article",
                5
        );

        waitForElementAndClick(
                By.className("android.widget.ImageButton"),
                "Cannot find 'Back button' to cancel search",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[contains(@content-desc, 'Saved')]"),
                "Cannot get to my 'Saved' articles",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[contains(@text, '" + name_of_my_folder +"')]"),
                "Cannot get to my created saved folded" + name_of_my_folder,
                5
        );

        swipeElementToLeft(
                By.xpath("//android.widget.TextView[contains(@text, 'Java (programming language)')]"),
                "Cannot find 2nd saved article"
        );

        waitForElementNotPresent(
                By.xpath("//android.widget.TextView[contains(@text, 'Java (programming language)')]"),
                "Cannot delete 2nd saved article",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.view.ViewGroup[@index='1']/android.widget.TextView"),
                "2nd article is not presented pn the screen",
                15
        );

        String title_after_saving = waitForElementAndGetAttribute(
                By.xpath("//android.view.View[contains(@text, 'JavaScript')]"),
                "text",
                "Cannot find the title of 1st article",
                15
        );

        Assert.assertEquals(
                "Title of the 1st article before saving is not equal to title in saved list folder",
                title_before_saving,
                title_after_saving
        );
    }

    @Test
    public void checkTitleOfArticleImmediately() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip button' input",
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
        waitForElementAndClick(
                By.xpath("//android.view.ViewGroup[@index='1']/android.widget.TextView"),
                "Cannot find 'java' text",
                15
        );

        assertElementPresent(
                By.xpath("//android.view.View[contains(@text, 'JavaScript')]"),
                "No results found for the desired title"
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

    protected void swipeUp (long typeOfSwipe){
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action
                .press(PointOption.point(x, start_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(typeOfSwipe)))
                .moveTo(PointOption.point(x, end_y))
                .release()
                .perform();
    }

    protected void swipeUpQuick() {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int maxSwipes) {

        int alreadySwiped = 0;

        while (driver.findElements(by).size() == 0) {

            if (alreadySwiped >= maxSwipes) {
                waitForElementPresent(by, "Cannot find element by swiping up. \n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++alreadySwiped;
        }
    }

    protected void swipeElementToLeft(By by, String error_message){
        WebElement element = waitForElementPresent(by, error_message, 10);
        int left_x = (int) ((element.getLocation().getX()) * 0.1);
        int right_x = (int) ((left_x + element.getSize().getWidth()) * 0.8);
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();


        action
                .press(PointOption.point(right_x, middle_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                .moveTo(PointOption.point(left_x, middle_y))
                .release()
                .perform();

    }

    private int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private  void assertElementNotPresent(By by, String error_message) {
        int amountOfElements = getAmountOfElements(by);
        if (amountOfElements > 0) {
            String default_message = "An element " + by.toString() + " supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeInSeconds);
        return element.getAttribute(attribute);
    }

    private  void assertElementPresent(By by, String error_message) {
        int amountOfElements = getAmountOfElements(by);
        if (amountOfElements <= 0) {
            String default_message = "An element " + by.toString() + " supposed to be presented at the page";
            throw new AssertionError(default_message + " " + error_message);
        }
    }
}
