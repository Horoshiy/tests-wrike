package tests.api;

import com.github.javafaker.Faker;
import config.ConfigHelper;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WrikeAPITests {
    @Test
    @DisplayName("get account info")
    void accountInfo() {
        String url = ConfigHelper.getApiUrl() + "account";
        String bearerToken = ConfigHelper.getApiToken();
        String clientId = ConfigHelper.getApiClient();

        AccountData response = given()
                .filter(new AllureRestAssured())
                .headers(
                        "Authorization",
                        "bearer " + bearerToken,
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .when()
                .get(url)
                .then()
                .statusCode(200)
                .extract()
                .as(AccountData.class);

        assertEquals(clientId, response.getData().get(0).getId());
    }

    @Test
    @DisplayName("post new task")
    void newTask() {
        String url = ConfigHelper.getApiUrl() + "folders/" + ConfigHelper.getApiFolder() + "/tasks";
        String bearerToken = ConfigHelper.getApiToken();
        Faker faker = new Faker();
        String taskTitle = faker.name().title();
        String clientId = ConfigHelper.getApiClient();

        TaskData response = given()
                .filter(new AllureRestAssured())
                .body("{ 'title': '" + taskTitle + "' }")
                .headers(
                        "Authorization",
                        "bearer " + bearerToken,
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .when()
                .post(url)
                .then()
                .statusCode(200)
                .extract()
                .as(TaskData.class);

        assertEquals(taskTitle, response.getData().get(0).getTitle());
        assertEquals(clientId, response.getData().get(0).getAccountId());
    }
}
