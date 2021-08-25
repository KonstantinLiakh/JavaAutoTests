package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchPageObject extends MainPageObject {

    private static final String
        SKIP_BUTTON_ON_MAIN_PAGE = "org.wikipedia:id/fragment_onboarding_skip_button",
        SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
        SEARCH_INPUT = "//*[contains(@text, 'Search Wikipedia')]",
        SEARCH_CANCEL_BUTTON = "//android.widget.ImageButton",
        SEARCH_RESULT_SUBSTRING_TPL = "//android.widget.TextView[contains(@text, '{SUBSTRING}')]",
        SEARCH_RESULT_TITLE_AND_DESCRIPTION_TPL = "//android.widget.TextView[@index='0' and @text='{SUBSTRING1}']/../android.widget.TextView[@index='1' and @text='{SUBSTRING2}']",
        SEARCH_RESULT_ELEMENT = "//android.view.ViewGroup[@index='0']/android.widget.TextView[@text='Linkin Park discography' and @index='0']",
        SEARCH_EMPTY_RESULT_ELEMENT = "//*[contains(@text, 'No results')]",
        TITLE_OF_ARTICLE0 = "//android.view.ViewGroup[@index='0']/android.widget.TextView[@index='0']",
        TITLE_OF_ARTICLE1 = "//android.view.ViewGroup[@index='1']/android.widget.TextView[@index='0']",
        TITLE_OF_ARTICLE2 = "//android.view.ViewGroup[@index='2']/android.widget.TextView[@index='0']",
        TITLE_OF_ARTICLE3 = "//android.view.ViewGroup[@index='3']/android.widget.TextView[@index='0']",
        TITLE_OF_ARTICLE4 = "//android.view.ViewGroup[@index='4']/android.widget.TextView[@index='0']",
        TITLE_OF_ARTICLE5 = "//android.view.ViewGroup[@index='5']/android.widget.TextView[@index='0']",
        TITLE_OF_ARTICLE6 = "//android.view.ViewGroup[@index='6']/android.widget.TextView[@index='0']",
        TITLE_OF_ARTICLE7 = "//android.view.ViewGroup[@index='7']/android.widget.TextView[@index='0']";

    public SearchPageObject(AppiumDriver driver) {

        super(driver);
    }
    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchElementWithDescription(String substring1, String substring2){
        return SEARCH_RESULT_TITLE_AND_DESCRIPTION_TPL
                .replace("{SUBSTRING1}", substring1)
                .replace("{SUBSTRING2}", substring2);
    }

    /* TEMPLATES METHODS*/

    public void initSearchInput() {
        this.waitForElementPresent(By.id(SKIP_BUTTON_ON_MAIN_PAGE), "Cannot find 'SKIP_BUTTON' on main page", 5 );
        this.waitForElementAndClick(By.id(SKIP_BUTTON_ON_MAIN_PAGE), "Cannot click on 'SKIP_BUTTON' on main page", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find 'Search Wikipedia' input after clicking by SEARCH_INIT_ELEMENT", 5 );
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot click on 'Search Wikipedia' input", 5);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(By.xpath(SEARCH_CANCEL_BUTTON), "Cannot find 'SEARCH_CANCEL_BUTTON' to cancel search",5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(By.xpath(SEARCH_CANCEL_BUTTON), "'SEARCH_CANCEL_BUTTON' is still on a page",5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(By.xpath(SEARCH_CANCEL_BUTTON), "Cannot find and click on 'SEARCH_CANCEL_BUTTON'",10);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT),search_line, "Cannot find and type into search input",5);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find SEARCH_RESULT with substring" + substring, 15);
    }

    public void waitForSearchResultWithDescription(String substring1, String substring2) {
        String search_result_xpath = getResultSearchElementWithDescription(substring1, substring2);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find SEARCH_RESULT with substrings", 15);
    }

    public void clickByArticleSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click SEARCH_RESULT with substring" + substring, 10);
    }

    public int getAmountOfFoundArticles() {

        this.waitForElementPresent(
                By.xpath(SEARCH_INIT_ELEMENT),
                "Cannot find anything by the request",
                15
        );

        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT), "Cannot find empty result by element", 15);

    }

    public void assertThereIsNoResultForSearch() {
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT), "We found some results by request ");
    }

    public void assertEachArticleTitleOnPageHasText() {

        WebElement element0 = this.waitForElementPresent(By.xpath(TITLE_OF_ARTICLE0), "Cannot find 'java' text", 15);
        WebElement element1 = this.waitForElementPresent(By.xpath(TITLE_OF_ARTICLE1), "Cannot find 'java' text", 15);
        WebElement element2 = this.waitForElementPresent(By.xpath(TITLE_OF_ARTICLE2), "Cannot find 'java' text", 15);
        WebElement element3 = this.waitForElementPresent(By.xpath(TITLE_OF_ARTICLE3), "Cannot find 'java' text", 15);
        WebElement element4 = this.waitForElementPresent(By.xpath(TITLE_OF_ARTICLE4), "Cannot find 'java' text", 15);
        WebElement element5 = this.waitForElementPresent(By.xpath(TITLE_OF_ARTICLE5), "Cannot find 'java' text", 15);
        WebElement element6 = this.waitForElementPresent(By.xpath(TITLE_OF_ARTICLE6), "Cannot find 'java' text", 15);
        WebElement element7 = this.waitForElementPresent(By.xpath(TITLE_OF_ARTICLE7), "Cannot find 'java' text", 15);

        this.assertElementHasText(element0, "java", "No text 'java'");
        this.assertElementHasText(element1, "java", "No text 'java'");
        this.assertElementHasText(element2, "java", "No text 'java'");
        this.assertElementHasText(element3, "java", "No text 'java'");
        this.assertElementHasText(element4, "java", "No text 'java'");
        this.assertElementHasText(element5, "java", "No text 'java'");
        this.assertElementHasText(element6, "java", "No text 'java'");
        this.assertElementHasText(element7, "java", "No text 'java'");
    }
}