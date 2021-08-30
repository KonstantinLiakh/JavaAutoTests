package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{

    private static final String
        TITLE_TPL = "xpath://android.view.View[contains(@text, '{ARTICLE_NAME}')]",
        FOOTER_ELEMENT = "xpath://*[contains(@text, 'View article in browser')]",
        SAVE_BUTTON = "xpath://android.widget.TextView[contains(@text, 'Save')]",
        ADD_TO_MY_LIST_BUTTON = "xpath://android.widget.Button[contains(@text, 'ADD TO LIST')]",
        MY_LIST_INPUT = "id:org.wikipedia:id/text_input",
        MY_LIST_OK_BUTTON = "xpath://android.widget.Button[contains(@text, 'OK')]",
        CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[contains(@content-desc, 'Navigate up')]",
        LIST_CREATED_BY_ME = "xpath://android.widget.TextView[contains(@text, 'List')]";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    private static String getTitleXpath(String article_name) {
        return TITLE_TPL.replace("{ARTICLE_NAME}", article_name);
    }

    public WebElement waitForTitleElement(String article_name) {

        String title_xpath = getTitleXpath(article_name);
        return this.waitForElementPresent(title_xpath, "Cannot find article title on a page", 15);
    }

    public void checkTitleElementWithoutWaitingTime(String article_name) {

        String title_xpath = getTitleXpath(article_name);
        this.assertElementPresent(
                title_xpath,
                "No results found for the desired title"
        );
    }
    public void swipeToFooter() {

        this.swipeUpToFindElement(
                FOOTER_ELEMENT,
                "Cannot find the end of article",
                20
        );
    }

    public void addArticleToMyList(String name_of_my_folder){
        this.waitForElementAndClick(
                SAVE_BUTTON,
                "cannot find find button for 'Save' the article",
                5
        );
        this.waitForElementAndClick(
                ADD_TO_MY_LIST_BUTTON,
                "cannot find find button for article to 'ADD TO LIST'",
                5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_INPUT,
                name_of_my_folder,
                "Cannot type text for 'My new list' saved items",
                5
        );
        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press 'OK' button to save articel to my list",
                5
        );
    }

    public void addArticleToAlreadyExistingList() {
        this.waitForElementAndClick(
                SAVE_BUTTON,
                "cannot find find button for 'Save' the article",
                5
        );
        this.waitForElementAndClick(
                ADD_TO_MY_LIST_BUTTON,
                "cannot find find button for article to 'ADD TO LIST'",
                5
        );
        this. waitForElementAndClick(
                LIST_CREATED_BY_ME,
                "Cannot press 'OK' button to save 2nd article to my list",
                5
        );
    }

    public void closeArticle() {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot close article",
                5
        );
    }
}