package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.BrowserstackDriver;
import drivers.LocalDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class TestBase {
    private static final String DEVICE_HOST = System.getProperty("deviceHost", "local");

    @BeforeAll
    static void beforeAll() {
        Configuration.browser = isBrowserstackSession()
                ? BrowserstackDriver.class.getName()
                : LocalDriver.class.getName();

        Configuration.browserSize = null;
        Configuration.timeout = 30000;
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Selenide.open();
    }

    @AfterEach
    void afterEach() {
        String sessionId = Selenide.sessionId().toString();
        System.out.println(sessionId);

        Attach.pageSource();
        closeWebDriver();

    }
}