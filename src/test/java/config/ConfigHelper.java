package config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigHelper {

    public static String getSiteLoginPage() {
        return getWebConfig().siteLoginPage();
    }

    public static String getSiteHomePage() {
        return getWebConfig().siteHomePage();
    }

    public static String getSiteUser() {
        return getWebConfig().siteUser();
    }

    public static String getSitePassword() {
        return getWebConfig().sitePassword();
    }

    public static String getWebdriverRemote() {
        return getWebConfig().webdriverRemote();
    }

    public static String getApiToken() {
        return getWebConfig().apiToken();
    }

    public static String getApiUrl() {
        return getWebConfig().apiURL();
    }

    public static String getApiClient() {
        return getWebConfig().apiClient();
    }

    public static String getApiFolder() {
        return getWebConfig().apiFolder();
    }

    public static WebConfig getWebConfig() {
        if (System.getProperty("environment") == null) System.setProperty("environment", "prod");

        return ConfigFactory.newInstance().create(WebConfig.class, System.getProperties());
    }
}
