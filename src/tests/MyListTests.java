package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListTests extends CoreTestCase {

    private static final String name_of_my_folder = "List_for_test";

    // ТЕСТ, КОТОРЫЙ СОХРАНЯЕТ СТАТЬЮ В НОВЫЙ СПИСОК ЗАКЛАДОК
    @Test
    public void testSaveFirstArticleToMyList(){

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleSubstring("Java (programming language)");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement("Java (programming language)");

        String article_title = ArticlePageObject.waitForTitleElement("Java (programming language)");

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_my_folder);
            ArticlePageObject.closeArticle();
            SearchPageObject.clickCancelSearch();
        }
        else {
            ArticlePageObject.addArticlesToMySavedIOS();
            ArticlePageObject.closeArticle();
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_my_folder);
        MyListsPageObject.swipeByArticleToDelete(article_title);
    }
    //ТЕСТ ДЛЯ СОХРАНЕНИЯ ДВУХ СТАТЕЙ В НОВЫЙ СПИСОК И ПОТОМ УДАЛЯЕТ ОДНУ СТАТЬЮ ИЗ СПИСКА
    @Test
    public void testSaveTwoArticlesAndDeleteOneOfIt(){

        //Save 1st article
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleSubstring("JavaScript");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement("JavaScript");

        String title_before_saving = ArticlePageObject.waitForTitleElement("JavaScript");

        ArticlePageObject.addArticleToMyList(name_of_my_folder);
        ArticlePageObject.closeArticle();

        //Save 2nd article
        SearchPageObject.clickByArticleSubstring("Java (programming language)");
        ArticlePageObject.waitForTitleElement("Java (programming language)");
        String article_to_be_deleted = ArticlePageObject.waitForTitleElement("Java (programming language)");
        ArticlePageObject.addArticleToAlreadyExistingList();
        ArticlePageObject.closeArticle();

        //Navigation to my lists
        SearchPageObject.clickCancelSearch();
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_my_folder);
        MyListsPageObject.swipeByArticleToDelete(article_to_be_deleted);

        SearchPageObject.clickByArticleSubstring("JavaScript");

        String title_after_saving = ArticlePageObject.waitForTitleElement("JavaScript");

        assertEquals(
                "Title of the 1st article before saving is not equal to title in saved list folder",
                title_before_saving,
                title_after_saving
        );
    }
}