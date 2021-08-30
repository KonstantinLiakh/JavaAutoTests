package lib.ui;

import io.appium.java_client.AppiumDriver;

public class NavigationUI extends MainPageObject{

    private static final String
        MY_LISTS_LINK = "xpath://android.widget.FrameLayout[contains(@content-desc, 'Saved')]";

    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }

    public void clickMyLists() {
        this.waitForElementAndClick(
                MY_LISTS_LINK,
                "Cannot get to my 'Saved' articles",
                5
        );
    }
}