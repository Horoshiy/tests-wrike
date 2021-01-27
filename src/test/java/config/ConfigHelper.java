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

    public static WebConfig getWebConfig() {
        if(System.getProperty("environment") == null) System.setProperty("environment", "prod");

        return ConfigFactory.newInstance().create(WebConfig.class, System.getProperties());
    }
}
