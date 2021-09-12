package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    // ТЕСТ ДЛЯ ПРОВЕРКИ ПОИСКА СТАТЬИ - done
    @Test
    public void testSearchObject() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Java (programming language)");
    }

    // ТЕСТ ДЛЯ ОТМЕНЫ ПОИСКА - done
    @Test
    public void testSearchCancel() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    //ТЕСТ ДЛЯ ПРОВЕРКИ ПОИСКА ПРИ НАЛИЧИИ РЕЗУЛЬТАТОВ В ПОИСКЕ
    @Test
    public void testAmountOfNotEmptySearch(){

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
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

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();

        String search_line = "liwdqwdawdasdsadasd";
        SearchPageObject.typeSearchLine(search_line);

        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultForSearch();
    }

    // ТЕСТ ДЛЯ ПРОВЕРКИ ПОИСКА СТАТЬИ ПО НАЗВАНИЮ И ОПИСАНИЮ
    @Test
    public void testSearchObjectWithDescription() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResultWithDescription("Java", "Indonesian island");
        SearchPageObject.waitForSearchResultWithDescription("JavaScript", "High-level programming language");
        SearchPageObject.waitForSearchResultWithDescription("Java (programming language)", "Object-oriented programming language");
    }
}