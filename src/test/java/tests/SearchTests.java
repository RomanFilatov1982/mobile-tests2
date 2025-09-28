package tests;

import org.junit.jupiter.api.Test;

public class SearchTests extends TestBase {

    @Test
    void successfulSearchTest() {
        openFeedPage()
                .skipOnboarding()
                .tapOnSearch()

                .typeSearchQuery("Дональд Трамп")
                .checkSearchResult()
                .clickOnSearchResultItem()

                .closeDialogPopup()
                .clickPageContents()
                .checkTopicsList()
                .clickOnFindInArticle()
                .findBlock("Страница обсуждения")
                .goToDiscissionPage()

                .checkTopicsCount()
                .findAndGoToActiveDiscussion()

                .checkReplyContents();
    }
}
