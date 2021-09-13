package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class iOSArticlePageObject extends ArticlePageObject {

    static {
        TITLE_TPL = "id:Java (programming language)";
        TITLE_TPL2 = "id:JavaScript";
        FOOTER_ELEMENT = "id:View article in browser";
        SAVE_BUTTON = "id:Save for later";
        CLOSE_DIALOG = "id:places auth close";
        CLOSE_ARTICLE_BUTTON = "id:Back";
    }

    public iOSArticlePageObject (AppiumDriver driver) {
        super(driver);
    }
}
