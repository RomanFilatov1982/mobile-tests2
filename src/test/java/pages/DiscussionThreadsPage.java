package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.anyMatch;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.id;

public class DiscussionThreadsPage {

    private final ElementsCollection
            TOPIC_TITLES = $$(id("org.wikipedia.alpha:id/topicTitleText")),
            TOPIC_REPLY_NUMBERS = $$(id("org.wikipedia.alpha:id/topicReplyNumber"));

    @Step("Verify topics count")
    public DiscussionThreadsPage checkTopicsCount(){
        TOPIC_TITLES.shouldHave(sizeGreaterThan(0), Duration.ofSeconds(5));
        return this;
    }

    @Step("Find and go to active discussion")
    public DiscussionRepliesPage findAndGoToActiveDiscussion() {
        TOPIC_REPLY_NUMBERS
                .shouldHave(anyMatch("Количество ответов больше 1",
                        (item)->{
                            return Integer.parseInt(item.getText()) > 1;
                        }))
                .get(0).click();
        return new DiscussionRepliesPage();
    }
}
