package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class iOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField";
        SEARCH_CANCEL_BUTTON = "id:Close";
        SEARCH_RESULT_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[contains(@name, '{SUBSTRING}')]";


        SEARCH_RESULT_TITLE_AND_DESCRIPTION_TPL = "xpath://android.widget.TextView[@index='0' and @text='{SUBSTRING1}']/../android.widget.TextView[@index='1' and @text='{SUBSTRING2}']";
        SEARCH_RESULT_ELEMENT = "xpath://android.view.ViewGroup[@index='0']/android.widget.TextView[@text='Linkin Park discography' and @index='0']";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";
    }

    public iOSSearchPageObject (AppiumDriver driver) {
        super(driver);
    }
}
