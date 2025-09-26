package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.DeviceConfig;
import config.RemoteConfig;
import drivers.BrowserstackDriver;
import drivers.LocalDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class TestBase {
    private static final DeviceConfig deviceConfig = ConfigFactory.create(DeviceConfig.class);
    private static final RemoteConfig remoteConfig = ConfigFactory.create(RemoteConfig.class);


    @BeforeAll
    static void beforeAll() {
        Configuration.remote = deviceConfig.deviceHost().equalsIgnoreCase("browserstack")
                ? remoteConfig.remoteUrl()
                : null;

        Configuration.browser = deviceConfig.deviceHost().equalsIgnoreCase("browserstack")
                ? BrowserstackDriver.class.getName()
                : LocalDriver.class.getName();

        Configuration.browserSize = null;
        Configuration.timeout = 30000;
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void afterEach() {
        String sessionId = Selenide.sessionId().toString();
        System.out.println(sessionId);

        Attach.pageSource();
        closeWebDriver();

    }
}