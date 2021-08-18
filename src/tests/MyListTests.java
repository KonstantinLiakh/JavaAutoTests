package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListTests extends CoreTestCase {

    // ТЕСТ, КОТОРЫЙ СОХРАНЯЕТ СТАТЬЮ В НОВЫЙ СПИСОК ЗАКЛАДОК
    @Test
    public void testSaveFirstArticleToMyList(){

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleSubstring("Java (programming language)");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement("Java (programming language)");

        String article_title = ArticlePageObject.waitForTitleElement("Java (programming language)").getAttribute("text");

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
    //ТЕСТ ДЛЯ СОХРАНЕНИЯ ДВУХ СТАТЕЙ В НОВЫЙ СПИСОК И ПОТОМ УДАЛЯЕТ ОДНУ СТАТЬЮ ИЗ СПИСКА
    @Test
    public void testSaveTwoArticlesAndDeleteOneOfIt(){

        //Save 1st article
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleSubstring("JavaScript");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement("JavaScript");

        String title_before_saving = ArticlePageObject.waitForTitleElement("JavaScript").getAttribute("text");
        String name_of_my_folder = "List_for_test";

        ArticlePageObject.addArticleToMyList(name_of_my_folder);
        ArticlePageObject.closeArticle();

        //Save 2nd article
        SearchPageObject.clickByArticleSubstring("Java (programming language)");
        ArticlePageObject.waitForTitleElement("Java (programming language)");
        String article_to_be_deleted = ArticlePageObject.waitForTitleElement("Java (programming language)").getAttribute("text");
        ArticlePageObject.addArticleToAlreadyExistingList();
        ArticlePageObject.closeArticle();

        //Navigation to my lists
        SearchPageObject.clickCancelSearch();
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_my_folder);
        MyListsPageObject.swipeByArticleToDelete(article_to_be_deleted);

        SearchPageObject.clickByArticleSubstring("JavaScript");

        String title_after_saving = ArticlePageObject.waitForTitleElement("JavaScript").getAttribute("text");

        assertEquals(
                "Title of the 1st article before saving is not equal to title in saved list folder",
                title_before_saving,
                title_after_saving
        );
    }
}