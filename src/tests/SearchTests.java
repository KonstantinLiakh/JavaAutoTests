package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

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

    //ТЕСТ ДЛЯ ПРОВЕРКИ ПОИСКА ПРИ НАЛИЧИИ РЕЗУЛЬТАТОВ В ПОИСКЕ
    @Test
    public void testAmountOfNotEmptySearch(){

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();

        String search_line = "Linkin Park Discography";

        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "We found a few results",
                amount_of_search_results > 0
        );
    }

    //ТЕСТ ДЛЯ ПРОВЕРКИ ПОИСКА ПРИ ПУСТОМ РЕЗУЛЬТАТЕ ПОИСКА
    @Test
    public void testAmountOfEmptySearch() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();

        String search_line = "liwdqwdawdasdsadasd";
        SearchPageObject.typeSearchLine(search_line);

        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultForSearch();
    }

    // ТЕСТ ДЛЯ ПРОВЕРКИ НАЗВАНИЯ КАЖДОЙ СТАТЬИ НА СТРАНИЦЕ, БЕЗ ПРОКРУТКИ

/*   Уточнить почему тест падает при локаторах, выбранных в методах теста
    @Test
    public void testArticlesTitlesContainText() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.assertEachArticleTitleOnPageHasText();
    }*/
}