package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class iOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField";
        SEARCH_CANCEL_BUTTON = "id:Close";
        SEARCH_RESULT_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[contains(@name, '{SUBSTRING}')]";

       // SEARCH_RESULT_TITLE_AND_DESCRIPTION_TPL = ""; - to refactor

        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeOther/XCUIElementTypeStaticText[contains(@name, 'Linkin Park discography)]";
        SEARCH_EMPTY_RESULT_ELEMENT = "id:No results found";
    }

    public iOSSearchPageObject (AppiumDriver driver) {
        super(driver);
    }
}
