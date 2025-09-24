package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.DeviceConfig;
import config.RemoteConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackDriver implements WebDriverProvider {
    private static final RemoteConfig remoteConfig = ConfigFactory.create(RemoteConfig.class);
    private static final DeviceConfig deviceConfig = ConfigFactory.create(DeviceConfig.class);



    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {

        MutableCapabilities caps = new MutableCapabilities();

        // Set your access credentials
        caps.setCapability("browserstack.user", remoteConfig.user());
        caps.setCapability("browserstack.key", remoteConfig.key());

        // Set URL of the application under test
        caps.setCapability("app", deviceConfig.appPackage());
        caps.setCapability("app", deviceConfig.appActivity());

        // Specify device and os_version for testing
        caps.setCapability("device", deviceConfig.deviceName());
        caps.setCapability("os_version", deviceConfig.osVersion());

        // Set other BrowserStack capabilities
        caps.setCapability("project", "First Java Project");
        caps.setCapability("build", "browserstack-build-1");
        caps.setCapability("name", "first_test");


        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
        try {
            return new RemoteWebDriver(
                    new URL(remoteConfig.remoteUrl()), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}