package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${deviceHost}.properties"
})
public interface DeviceConfig extends Config {
    @Key("deviceName")
    String deviceName();

    @Key("osVersion")
    String osVersion();

    @Key("deviceHost")
    @DefaultValue("real")
    String deviceHost();

    @Key("appPackage")
    String appPackage();

    @Key("appActivity")
    String appActivity();

}
