package pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.id;

public class DiscussionRepliesPage {

    private final ElementsCollection REPLY_BODY_TEXTS = $$(id("org.wikipedia.alpha:id/bodyText"));

    @Step("Verify reply count")
    public DiscussionRepliesPage checkReplyContents(){
        REPLY_BODY_TEXTS.shouldHave(
                sizeGreaterThan(1),
                Duration.ofSeconds(15));
        return this;
    }
}
