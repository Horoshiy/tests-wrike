package tests;

import com.github.javafaker.Faker;
import config.ConfigHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class WrikeUITests extends TestBase {

    @Test
    @DisplayName("add new project to workspace")
    void createNewProjectOnGreenPlusClickTest() {
        step("open main account page", () -> {
            open(ConfigHelper.getSiteHomePage());
            $("button.work-creation-button__control").click();
            $(byText("Проект или папка")).click();
            $("pcw-form").shouldHave(text("Создать проект"));
        });

        step("create new project", () -> {
            Faker faker = new Faker();
            String title = faker.name().title();
            $x("//input[@placeholder='Название проекта']").val(title);
            $("button.pcw-form-button").click();
            Thread.sleep(5000);
            $("main").shouldBe(visible).shouldHave(text(title));
        });

    }
}
