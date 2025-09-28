package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.id;
import static org.openqa.selenium.By.xpath;

public class ArticlePage {
    private final SelenideElement
            DIALOG_CONTAINER = $(id("org.wikipedia.alpha:id/dialogContainer")),
            PAGE_CONTENTS_BUTTON = $(id("org.wikipedia.alpha:id/page_contents")),
            PAGE_CONTENT_CONTAINER = $(id("org.wikipedia.alpha:id/page_contents_container")),
            FIND_IN_ARTICLE_BUTTON = $(id("org.wikipedia.alpha:id/page_find_in_article")),
            SEARCH_TEXT_FIELD = $(id("org.wikipedia.alpha:id/search_src_text")),
            CLOSE_SEARCH_BUTTON = $(id("org.wikipedia.alpha:id/action_mode_close_button")),
            DISCUSSION_PAGE_MENU_ITEM = $(xpath("//android.widget.TextView[@text=\"Страница обсуждения\"]"))
            ;
    private final ElementsCollection TOPIC_ITEMS = $$(id("org.wikipedia.alpha:id/page_toc_item_text"));
    private final By CLOSE_DIALOG_BUTTON_SELECTOR = id("org.wikipedia.alpha:id/closeButton");

    @Step("Close dialog window")
    public ArticlePage closeDialogPopup(){
        DIALOG_CONTAINER
                .shouldBe(visible, Duration.ofSeconds(15))
                .find(CLOSE_DIALOG_BUTTON_SELECTOR)
                .click();
        return new ArticlePage();
    }

    @Step("Click on page content")
    public ArticlePage clickPageContents(){
        PAGE_CONTENTS_BUTTON.click();
        return new ArticlePage();
    }

    @Step("Verify topic list")
    public ArticlePage checkTopicsList(){
        TOPIC_ITEMS.shouldHave(sizeGreaterThan(0), Duration.ofSeconds(15));
        PAGE_CONTENT_CONTAINER.click();
        return new ArticlePage();
    }

    @Step("Click on Find In Article")
    public ArticlePage clickOnFindInArticle() {
        FIND_IN_ARTICLE_BUTTON.click();
        return new ArticlePage();
    }

    @Step("Find Content Block at Page")
    public ArticlePage findBlock(String title) {
        SEARCH_TEXT_FIELD.sendKeys(title);
        CLOSE_SEARCH_BUTTON.click();
        return new ArticlePage();
    }

    @Step("Go to discussion page")
    public DiscussionThreadsPage goToDiscissionPage(){
        DISCUSSION_PAGE_MENU_ITEM.click();
        return new DiscussionThreadsPage();
    }
}
