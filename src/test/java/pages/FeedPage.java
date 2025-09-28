package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.className;
import static io.appium.java_client.AppiumBy.id;

public class FeedPage {

    private static final SelenideElement
        SKIP_BUTTON = $(id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")),
        SEARCH_CONTAINER = $(id("org.wikipedia.alpha:id/search_container"));

    private static final By SEARCH_FIELD_CLASS = className("android.widget.TextView");

    @Step("Skip onboarding")
    public FeedPage skipOnboarding() {
        if (SKIP_BUTTON.isDisplayed()) {
            SKIP_BUTTON.click();
        }
        return this;
    }

    @Step("Tap on Search container")
    public SearchPage tapOnSearch() {
        SEARCH_CONTAINER
                .find(SEARCH_FIELD_CLASS)
                .click();
        return new SearchPage();
    }
}
