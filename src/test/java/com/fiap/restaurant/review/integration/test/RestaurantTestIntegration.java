package com.fiap.restaurant.review.integration.test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalTime;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.fiap.restaurant.review.application.records.restaurant.AddressRecord;
import com.fiap.restaurant.review.application.records.restaurant.FilterRestaurantRecord;
import com.fiap.restaurant.review.application.records.restaurant.RestaurantRecord;
import com.fiap.restaurant.review.integration.config.TestConfigs;
import com.fiap.restaurant.review.integration.containers.AbstractIntegrationTest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RestaurantTestIntegration extends AbstractIntegrationTest {

    private static RequestSpecification specification;
    private static String STRING_RESPONSE = "{"
            + "\"id\":1,"
            + "\"name\":\"teste\","
            + "\"cnpj\":\"12345678910111\","
            + "\"phone\":\"40028922\","
            + "\"foodType\":\"teste\","
            + "\"openHour\":\"00:00:00\","
            + "\"closeHour\":\"00:00:00\","
            + "\"alwaysOpen\":true,"
            + "\"totalCapacity\":1,"
            + "\"totalGrade\":1,"
            + "\"address\":{"
            + "\"id\":1,"
            + "\"cep\":\"12345678\","
            + "\"publicPlace\":\"teste\","
            + "\"complement\":\"teste\","
            + "\"neighborhood\":\"teste\","
            + "\"city\":\"teste\","
            + "\"ufState\":\"te\","
            + "\"latitude\":0.0,"
            + "\"longitude\":0.0"
            + "}"
            + "}";

    @Test
    @Order(0)
    void createRestaurantTeste() {

        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_FRONT)
                .setBasePath("/api/restaurant/save")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        final var response = given()
                .spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(new RestaurantRecord(
                        "teste", "12345678910111", "40028922", "teste", LocalTime.of(0, 0, 0), LocalTime.of(0, 0, 0),
                        true, 1, 1,
                        new AddressRecord("12345678", "teste", "teste", "teste", "teste", "te", 0.0, 0.0)))
                .when().post().then()
                .statusCode(200)
                .extract().body().asString();

        assertNotNull(response);
        assertEquals(STRING_RESPONSE, response);
    }

    @Test
    @Order(1)
    void findAllRestaurantTeste() {

        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_FRONT)
                .setBasePath("/api/restaurant/all")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        final var response = given().spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .header(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_FRONT)
                .when().get().then()
                .statusCode(200)
                .extract().body().asString();

        assertNotNull(response);
        assertEquals("[" + STRING_RESPONSE + "]", response);
    }

    @Test
    @Order(2)
    void findRestaurantByFilterTeste() {

        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_FRONT)
                .setBasePath("/api/restaurant/filter")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        final var response = given().spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .header(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_FRONT)
                .body(new FilterRestaurantRecord("teste", "teste", "teste"))
                .when().get().then()
                .statusCode(200)
                .extract().body().asString();

        assertNotNull(response);
        assertEquals("[" + STRING_RESPONSE + "]", response);
    }

}
