package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.id;

public class SearchPage {
    private final SelenideElement
        SEARCH_FIELD = $(id("org.wikipedia.alpha:id/search_src_text"));
    private final ElementsCollection
        SEARCH_RESULT_ITEM_TITLES =  $$(id("org.wikipedia.alpha:id/page_list_item_title"));

    @Step("Type search")
    public SearchPage typeSearchQuery(String query) {
        SEARCH_FIELD.sendKeys(query);
        return this;
    }

    @Step("Verify content found")
    public SearchPage checkSearchResult() {
        SEARCH_RESULT_ITEM_TITLES.shouldHave(sizeGreaterThan(0));
        return this;
    }

    @Step("Click on result item")
    public ArticlePage clickOnSearchResultItem() {
        SEARCH_RESULT_ITEM_TITLES.get(0).click();
        return new ArticlePage();
    }
}
