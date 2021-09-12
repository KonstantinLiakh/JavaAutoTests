package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

    // ТЕСТ, КОТОРЫЙ ПРОВЕРЯЕТ НАЗВАНИЕ СТАТЬИ - done
    @Test
    public void testCompareArticleTitle() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleSubstring("Java (programming language)");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        String article_title = ArticlePageObject.waitForTitleElement("Java (programming language)");

        assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title
        );
    }

    // ТЕСТ ДЛЯ ПРОВЕРКИ СКРОЛЛИНГА СТРАНИЦЫ ВНИЗ - done
    @Test
    public void testSwipeArticle() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleSubstring("Java (programming language)");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement("Java (programming language)");
        ArticlePageObject.swipeToFooter();
    }

    // ТЕСТ, КОТОРЫЙ ПРОВЕРЯЕТ НАЗВАНИЕ СТАТЬИ, НЕ ДОЖИДАЯСЬ САМОГО ЭЛЕМЕНТА С НАЗВАНИЕМ
    @Test
    public void testTitleOfArticleImmediately() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("JavaScript");
        SearchPageObject.clickByArticleSubstring("JavaScript");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.checkTitleElementWithoutWaitingTime("Javascript");
    }
}