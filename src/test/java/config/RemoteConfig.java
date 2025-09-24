package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:remote.properties"
})
public interface RemoteConfig extends Config {
    @Key("user")
    String user();

    @Key("key")
    String key();

    @Key("remoteUrl")
    String remoteUrl();
}
