package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.RemoteWebElement;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.*;
import static io.qameta.allure.Allure.step;

public class SearchTests extends TestBase {

    @Test
    void successfulSearchTest() {
        back();
        step("Skip onboarding", () -> {
            if ($(id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).isDisplayed()) {
                $(id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click();
            }
        });
        step("Type search", () -> {
            $(id("org.wikipedia.alpha:id/search_container"))
                    .find(className("android.widget.TextView")).click();
            $(id("org.wikipedia.alpha:id/search_src_text"))
                    .sendKeys("Дональд Трамп");
        });
        step("Verify content found", () -> {
            $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                    .shouldHave(sizeGreaterThan(0));
        });

        step("Click on result item", () -> {
            $$(id("org.wikipedia.alpha:id/page_list_item_title")).get(0).click();
        });

        step("Close dialog window", () -> {
            $(id("org.wikipedia.alpha:id/dialogContainer")).shouldBe(visible, Duration.ofSeconds(15))
                    .find(id("org.wikipedia.alpha:id/closeButton")).click();
        });

        step("Click on page content", () -> {
            $(id("org.wikipedia.alpha:id/page_contents")).click();
        });

        step("Verify topic list", () -> {
            $$(id("org.wikipedia.alpha:id/page_toc_item_text"))
                    .shouldHave(sizeGreaterThan(0));
        });

        step("Go to last topic", () ->{

       /*     //$(id("org.wikipedia.alpha:id/side_panel_container"));
            AndroidDriver driver = (AndroidDriver) WebDriverRunner.getWebDriver();
            Dimension size = driver.manage().window().getSize();
            //int endX = $(id("org.wikipedia.alpha:id/page_scroller_button")).getCoordinates().onPage().x;
            int endX = (int) (size.width * 0.7);
            int endY = (int) (size.height * 0.7);

            driver.executeScript("mobile: dragGesture", ImmutableMap.of(
                    "elementId", "org.wikipedia.alpha:id/page_scroller_button", //((RemoteWebElement) $(id("org.wikipedia.alpha:id/page_scroller_button"))).getId(),
                    "endX", endX,
                    "endY", endY
            ));*/
            $(id("org.wikipedia.alpha:id/page_contents_container")).click();
            $(id("org.wikipedia.alpha:id/page_find_in_article")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Страница обсуждения");

           /* $$(id("org.wikipedia.alpha:id/page_toc_item_text")).last()
                    .scrollIntoView(true).click();*/
        });

        step("Go to discussion page", () -> {
            $(id("pcs-footer-container-menu-items")).find(xpath("//android.widget.TextView[@text=\"Страница обсуждения\"]")).click();

           /* .findAll(className("android.widget.TextView"))
                    .find(text("Страница обсуждения")).click();*/
        });

    }
}
