package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

    // ТЕСТ, КОТОРЫЙ ПРОВЕРЯЕТ НАЗВАНИЕ СТАТЬИ
    @Test
    public void testCompareArticleTitle() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleSubstring("Java (programming language)");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String article_title = ArticlePageObject.waitForTitleElement("Java (programming language)").getAttribute("text");

        assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title
        );
    }

    // ТЕСТ ДЛЯ ПРОВЕРКИ СКРОЛЛИНГА СТРАНИЦЫ ВНИЗ
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

    // ТЕСТ, КОТОРЫЙ ПРОВЕРЯЕТ НАЗВАНИЕ СТАТЬИ, НЕ ДОЖИДАЯСЬ САМОГО ЭЛЕМЕНТА С НАЗВАНИЕМ
    @Test
    public void testTitleOfArticleImmediately() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("JavaScript");
        SearchPageObject.clickByArticleSubstring("JavaScript");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.checkTitleElementWithoutWaitingTime("Javascript");
    }
}