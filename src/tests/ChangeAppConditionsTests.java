package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ChangeAppConditionsTests extends CoreTestCase {

    //ТЕСТ ДЛЯ ПРОВЕРКИ НАЗВАНИЯ СТАТЬИ ПОСЛЕ СМЕНЫ ОРИЕНТАТЦИИ УСТРОЙСТВА
    @Test
    public void testChangeOrientationScreenInSearchResults(){

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleSubstring("Java (programming language)");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String title_before_rotation = ArticlePageObject.waitForTitleElement("Java (programming language)");
        this.rotateScreenLandscape();
        String title_after_rotation = ArticlePageObject.waitForTitleElement("Java (programming language)");

        assertEquals(
                "Article title has been changed after rotation",
                title_before_rotation,
                title_after_rotation
        );

        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePageObject.waitForTitleElement("Java (programming language)");

        assertEquals(
                "Article title has been changed after rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }

    //ТЕСТ ДЛЯ ПРОВЕРКИ ЭЛЕМЕНТА СТРАНИЦЫ ПОСЛЕ ОТКРЫТИЯ ПРИЛОЖЕНИЯ ИЗ БЭКГРАУНДА
    @Test
    public void testArticleTextAfterBackground(){

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Java (programming language)");
        this.backgroudApp(2);
        SearchPageObject.waitForSearchResult("Java (programming language)");
    }

}