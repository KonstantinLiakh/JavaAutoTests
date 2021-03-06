package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

 abstract public class MyListsPageObject extends MainPageObject{

    protected static String
        FOLDER_BY_NAME_TPL,
        ARTICLE_BY_TITLE_TPL,
        ARTICLE_DELETE_BUTTON_FOR_IOS;

    private static String getFolderXpathByName(String name_of_my_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_my_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    public MyListsPageObject (AppiumDriver driver) {
        super(driver);
    }

    public void openFolderByName(String name_of_my_folder) {
        String folder_name_xpath = getFolderXpathByName(name_of_my_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot get folder by name" + name_of_my_folder,
                5
        );
    }

    public void waitForArticleToAppearByTitle (String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(
                article_xpath,
                "Cannot find saved article" + article_title,
                5
        );
    }

    public void swipeByArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToLeft(
                article_xpath,
                "Cannot find saved article"
        );
        if (Platform.getInstance().isIOS()) {
            this.waitForElementAndClick(ARTICLE_DELETE_BUTTON_FOR_IOS,
                    "Cannot find delete article button for iOS",
                    5
            );
        }

        this.waitForArticleToDisappear_ByTitle(article_title);
    }

    public void waitForArticleToDisappear_ByTitle (String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(
                article_xpath,
                "Cannot delete saved article, it's still present with title" + article_title,
                5
        );
    }
}