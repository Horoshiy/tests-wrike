package tests.ui;

import config.ConfigHelper;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class UiTestBase extends TestBase {

    @BeforeAll
    @Step("sign in")
    static void fillSignInFormTest() throws InterruptedException {
        String url = ConfigHelper.getSiteHomePage();
        String signInUrl = ConfigHelper.getSiteLoginPage();
        String name = ConfigHelper.getSiteUser();
        String password = ConfigHelper.getSitePassword();

        open(url);
        $("body").shouldHave(text("Wrike"));
        $(by("href", signInUrl)).click();
        $(".login-bottom__text").shouldHave(text("Don't have a Wrike account yet?"));
        $("input.login-input").val(name);
        $("div.button-text").click();
        $("login-password").shouldHave(text("Enter your password"));
        $("input.login-input").val(password);
        $("button.button-style-fullwidth").click();
        Thread.sleep(5000); // TODO: очень плохо, придумать другой способ
        $("div.login-info__title").shouldBe(visible).shouldHave(text("Remember this browser next time?"));
        $(by("data-application", "login-forget")).click();
        Thread.sleep(5000); // TODO: очень плохо, придумать другой способ
        $("home-page-link").shouldBe(visible);
    }
}
