package tests;

import com.codeborne.selenide.Configuration;
import config.ConfigHelper;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static config.AttachmentsHelper.*;
import static io.qameta.allure.Allure.step;

public class TestBase {
    @BeforeAll
    static void setup() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
//        Configuration.remote = ConfigHelper.getWebdriverRemote();
        Configuration.startMaximized = true;
        fillSignInFormTest();
    }

    static void fillSignInFormTest() {
        String url = ConfigHelper.getSiteHomePage();
        String signInUrl = ConfigHelper.getSiteLoginPage();
        String name = ConfigHelper.getSiteUser();
        String password = ConfigHelper.getSitePassword();

        step("open Main Page", () -> {
            open(url);
            $("body").shouldHave(text("Wrike"));
        });

        step("click on Log in button", () -> {
            $(by("href", signInUrl)).click();
            $(".login-bottom__text").shouldHave(text("Don't have a Wrike account yet?"));
        });

        step("fill login form", () -> {
            $("input.login-input").val(name);
            $("div.button-text").click();
            $("login-password").shouldHave(text("Enter your password"));
        });

        step("fill password form", () -> {
            $("input.login-input").val(password);
            $("button.button-style-fullwidth").click();
            Thread.sleep(5000); // TODO: очень плохо, придумать другой способ
            $("div.login-info__title").shouldBe(visible).shouldHave(text("Remember this browser next time?"));
        });

        step("disable remember browser", () -> {
            $(by("data-application", "login-forget")).click();
            Thread.sleep(5000); // TODO: очень плохо, придумать другой способ
            $("home-page-link").shouldBe(visible);
        });
    }

    @AfterEach
    @Step("Attachments")
    public void afterEach() {
        attachScreenshot("Last screenshot");
        attachPageSource();
        attachAsText("Browser console logs", getConsoleLogs());
    }
}
