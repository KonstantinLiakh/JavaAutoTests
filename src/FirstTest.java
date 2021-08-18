import junit.framework.Assert;
import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import java.time.Duration;

public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

    // ТЕСТ ДЛЯ ПРОВЕРКИ ПОИСКА СТАТЬИ
    @Test
    public void testSearchObject() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Java (programming language)");
    }

    // ТЕСТ ДЛЯ ОТМЕНЫ ПОИСКА
    @Test
    public void testSearchCancel() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    // ТЕСТ, КОТОРЫЙ ПРОВЕРЯЕТ НАЗВАНИЕ СТАТЬИ
    @Test
    public void testCompareArticleTitle() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleSubstring("Java (programming language)");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        String article_title = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title
        );
    }

    @Test
    public void testSwipeArticle() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleSubstring("Appium");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement("Appium");
        ArticlePageObject.swipeToFooter();
    }

    // Тест для проверки названия каждой статьи на странице
    @Test
    public void testArticlesContainText() {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "java",
                "Cannot find search input",
                5
        );

        WebElement element0 = MainPageObject.waitForElementPresent(
                By.xpath("//android.view.ViewGroup[@index='0']/android.widget.TextView"),
                "Cannot find 'java' text",
                15
        );
        WebElement element1 = MainPageObject.waitForElementPresent(
                By.xpath("//android.view.ViewGroup[@index='1']/android.widget.TextView"),
                "Cannot find 'java' text",
                15
        );
        WebElement element2 = MainPageObject.waitForElementPresent(
                By.xpath("//android.view.ViewGroup[@index='0']/android.widget.TextView"),
                "Cannot find 'java' text",
                15
        );
        WebElement element3 = MainPageObject.waitForElementPresent(
                By.xpath("//android.view.ViewGroup[@index='1']/android.widget.TextView"),
                "Cannot find 'java' text",
                15
        );
        WebElement element4 = MainPageObject.waitForElementPresent(
                By.xpath("//android.view.ViewGroup[@index='0']/android.widget.TextView"),
                "Cannot find 'java' text",
                15
        );
        WebElement element5 = MainPageObject.waitForElementPresent(
                By.xpath("//android.view.ViewGroup[@index='1']/android.widget.TextView"),
                "Cannot find 'java' text",
                15
        );
        WebElement element6 = MainPageObject.waitForElementPresent(
                By.xpath("//android.view.ViewGroup[@index='0']/android.widget.TextView"),
                "Cannot find 'java' text",
                15
        );
        WebElement element7 = MainPageObject.waitForElementPresent(
                By.xpath("//android.view.ViewGroup[@index='1']/android.widget.TextView"),
                "Cannot find 'java' text",
                15
        );

        MainPageObject.assertElementHasText(
                element0,
                "java",
                "No text 'java'"
        );

        MainPageObject.assertElementHasText(
                element1,
                "java",
                "No text 'java'"
        );
        MainPageObject.assertElementHasText(
                element2,
                "java",
                "No text 'java'"
        );

        MainPageObject.assertElementHasText(
                element3,
                "java",
                "No text 'java'"
        );
        MainPageObject.assertElementHasText(
                element4,
                "java",
                "No text 'java'"
        );

        MainPageObject.assertElementHasText(
                element5,
                "java",
                "No text 'java'"
        );
        MainPageObject.assertElementHasText(
                element6,
                "java",
                "No text 'java'"
        );

        MainPageObject.assertElementHasText(
                element7,
                "java",
                "No text 'java'"
        );
    }

    @Test
    public void testSaveFirstArticleToMyList(){

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleSubstring("Java (programming language)");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement("Java (programming language)");

        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_my_folder = "My new list";
        ArticlePageObject.addArticleToMyList(name_of_my_folder);
        ArticlePageObject.closeArticle();

        SearchPageObject.clickCancelSearch();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_my_folder);
        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testAmountOfNotEmptySearch(){
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip button' input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_line = "Linkin Park Discography";

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find 'Linkin Park Discography",
                5
        );

        String search_result_locator = "//android.view.ViewGroup[@index='0']/android.widget.TextView[@text='Linkin Park discography' and @index='0']";

        MainPageObject.waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything by the request" + search_line,
                15
        );

        int amount_of_search_results = MainPageObject.getAmountOfElements(
                By.xpath(search_result_locator)
        );
        Assert.assertTrue(
                "We found a few results",
                amount_of_search_results > 0
        );

    }

    @Test
    public void testAmountOfEmptySearch() {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip button' input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_line = "liwdqwdawdasdsadasd";

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find 'liwdqwdawdasdsadasd",
                5
        );

        String search_result_locator = "//android.view.ViewGroup[@index='0']/android.widget.TextView[@text='Linkin Park discography' and @index='0']";
        String empty_result_label = "//*[contains(@text, 'No results')]";

        MainPageObject.waitForElementPresent(
                By.xpath(empty_result_label),
                "Cannot find empty result label by the request " + search_line,
                15
        );

        MainPageObject.assertElementNotPresent(
                By.xpath(search_result_locator),
                "We found some results by request " + search_line
        );

    }

    @Test
    public void testChangeOrientationScreenInSearchResults(){
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip button' input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_line = "java";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find search input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[contains(@text, 'Java (programming language)')]"),
                "Cannot find 'Java (programming language)' text by " + search_line,
                15
        );

        String title_before_rotation = MainPageObject.waitForElementAndGetAttribute(
                By.xpath("//android.view.View[contains(@text, 'Java (programming language)')]"),
                "text",
                "Cannot find the title of article",
                15
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = MainPageObject.waitForElementAndGetAttribute(
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

        String title_after_second_rotation = MainPageObject.waitForElementAndGetAttribute(
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
    public void testArticleTextAfterBackground(){
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip button' input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_line = "java";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//android.view.ViewGroup[@index='2']/android.widget.TextView[@text='Java (programming language)']"),
                "Cannot find article in search results",
                15
        );

        driver.runAppInBackground(Duration.ofSeconds(2));

        MainPageObject.waitForElementPresent(
                By.xpath("//android.view.ViewGroup[@index='2']/android.widget.TextView[@text='Java (programming language)']"),
                "Cannot find article in search results after returning from background",
                15
        );
    }

    @Test
    public void testSaveTwoArticlesAndDeleteOneOfIt(){
        //methods to remember 1st article title, save it and go back to articles list
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip button' input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "java",
                "Cannot find search input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.view.ViewGroup[@index='1']/android.widget.TextView"),
                "Cannot find 'java' text",
                15
        );

        String title_before_saving = MainPageObject.waitForElementAndGetAttribute(
                By.xpath("//android.view.View[contains(@text, 'JavaScript')]"),
                "text",
                "Cannot find the title of 1st article",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[contains(@text, 'Save')]"),
                "Cannot find find button to 'Save' 1st article",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.Button[contains(@text, 'ADD TO LIST')]"),
                "Cannot find find button for 1st article to 'ADD TO LIST'",
                5
        );

        String name_of_my_folder = "List_for_test";

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_my_folder,
                "Cannot type text 'List_for_test' for new saved list",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.Button[contains(@text, 'OK')]"),
                "Cannot press 'OK' button to save 1st article to my list",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[contains(@content-desc, 'Navigate up')]"),
                "Cannot close 1st article",
                5
        );

        //methods to save 2nd article
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.view.ViewGroup[@index='2']/android.widget.TextView"),
                "Cannot find 'java' text",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[contains(@text, 'Save')]"),
                "Cannot find find button 'Save' to save 2nd article",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.Button[contains(@text, 'ADD TO LIST')]"),
                "Cannot find find button for 2nd article to 'ADD TO LIST'",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[contains(@text, 'List')]"),
                "Cannot press 'OK' button to save 2nd article to my list",
                5
        );
        //Go to saved list, delete 2nd article and keep 1st article
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[contains(@content-desc, 'Navigate up')]"),
                "Cannot close 2nd article",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton"),
                "Cannot find 'Back button' to cancel search",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[contains(@content-desc, 'Saved')]"),
                "Cannot get to my 'Saved' articles",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[contains(@text, '" + name_of_my_folder +"')]"),
                "Cannot get to my created saved folded" + name_of_my_folder,
                5
        );

        MainPageObject.swipeElementToLeft(
                By.xpath("//android.widget.TextView[contains(@text, 'Java (programming language)')]"),
                "Cannot find 2nd saved article"
        );

        MainPageObject.waitForElementNotPresent(
                By.xpath("//android.widget.TextView[contains(@text, 'Java (programming language)')]"),
                "Cannot delete 2nd saved article",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.view.ViewGroup[@index='1']/android.widget.TextView"),
                "2nd article is not presented pn the screen",
                15
        );

        String title_after_saving = MainPageObject.waitForElementAndGetAttribute(
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
    public void testTitleOfArticleImmediately() {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip button' input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "java",
                "Cannot find search input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.view.ViewGroup[@index='1']/android.widget.TextView"),
                "Cannot find 'java' text",
                15
        );

        MainPageObject.assertElementPresent(
                By.xpath("//android.view.View[contains(@text, 'JavaScript')]"),
                "No results found for the desired title"
        );

    }
}
