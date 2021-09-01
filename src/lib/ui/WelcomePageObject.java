package lib.ui;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject{

    private static final String
    STEP_LEARN_MORE_LINK = "xpath://XCUIElementTypeStaticText[@name='Learn more about Wikipedia']",
    STEP_NEW_WAYS_TO_EXPLORE = "id:New ways to explore",
    STEP_ADD_AND_EDIT_PREFERRED_LANG = "xpath://XCUIElementTypeStaticText[@name='Add or edit preferred languages']",
    STEP_LEARN_MORE_ABOUT_DATA_COLLECTED = "xpath://XCUIElementTypeStaticText[@name='Learn more about data collected']",
    NEXT_BUTTON = "xpath://XCUIElementTypeButton[@name='Next']",
    GET_STARTED_BUTTON = "xpath://XCUIElementTypeButton[@name='Get started']";

    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForMoreLink() {
        this.waitForElementPresent(STEP_LEARN_MORE_LINK,
                "Cannot find 'Learn more about Wikipedia' link",
                10
        );
    }

    public void waitForNewWaysToExplore() {
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE,
                "Cannot find 'New ways to explore' text",
                10
        );
    }

    public void waitForAddAndEditPreferredLangText() {
        this.waitForElementPresent(STEP_ADD_AND_EDIT_PREFERRED_LANG,
                "Cannot find 'Add or edit preferred languages' text",
                10
        );
    }

    public void waitForLearnMoreAboutDataCollectedText() {
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED,
                "Cannot find 'Learn more about data collected' text",
                10
        );
    }

    public void clickNextButton() {
        this.waitForElementAndClick(NEXT_BUTTON,
                "Cannot find 'Next' button",
                10
        );
    }

    public void clickGetStartedButton() {
        this.waitForElementAndClick(GET_STARTED_BUTTON,
                "Cannot find 'Get started' button",
                10
        );
    }
}
