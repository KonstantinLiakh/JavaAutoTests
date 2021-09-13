package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.WelcomePageObject;

public class AndroidWelcomePageObject extends WelcomePageObject {

    static {
        SKIP_BUTTON_ON_MAIN_PAGE = "id:org.wikipedia:id/fragment_onboarding_skip_button";
    }

    public AndroidWelcomePageObject (AppiumDriver driver) {
        super(driver);
    }
}
