package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:${environment}.properties"
})
public interface WebConfig extends Config {
    @Key("webdriver.remote")
    String webdriverRemote();

    @Key("site.login")
    String siteLoginPage();

    @Key("site.home")
    String siteHomePage();

    @Key("site.user")
    String siteUser();

    @Key("site.password")
    String sitePassword();

    @Key("api.token")
    String apiToken();

    @Key("api.url")
    String apiURL();

    @Key("api.client")
    String apiClient();

    @Key("api.folder")
    String apiFolder();
}
