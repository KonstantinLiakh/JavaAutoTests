package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ChangeAppConditionsTests extends CoreTestCase {

    //ТЕСТ ДЛЯ ПРОВЕРКИ НАЗВАНИЯ СТАТЬИ ПОСЛЕ СМЕНЫ ОРИЕНТАТЦИИ УСТРОЙСТВА
    @Test
    public void testChangeOrientationScreenInSearchResults(){

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleSubstring("Java (programming language)");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String title_before_rotation = ArticlePageObject.waitForTitleElement("Java (programming language)").getAttribute("text");
        this.rotateScreenLandscape();
        String title_after_rotation = ArticlePageObject.waitForTitleElement("Java (programming language)").getAttribute("text");

        assertEquals(
                "Article title has been changed after rotation",
                title_before_rotation,
                title_after_rotation
        );

        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePageObject.waitForTitleElement("Java (programming language)").getAttribute("text");

        assertEquals(
                "Article title has been changed after rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }

    //ТЕСТ ДЛЯ ПРОВЕРКИ ЭЛЕМЕНТА СТРАНИЦЫ ПОСЛЕ ОТКРЫТИЯ ПРИЛОЖЕНИЯ ИЗ БЭКГРАУНДА
    @Test
    public void testArticleTextAfterBackground(){

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Java (programming language)");
        this.backgroudApp(2);
        SearchPageObject.waitForSearchResult("Java (programming language)");
    }

}