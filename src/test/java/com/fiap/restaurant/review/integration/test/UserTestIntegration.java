package com.fiap.restaurant.review.integration.test;

import com.fiap.restaurant.review.application.records.user.UserRecord;
import com.fiap.restaurant.review.integration.config.TestConfigs;
import com.fiap.restaurant.review.integration.containers.AbstractIntegrationTest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserTestIntegration extends AbstractIntegrationTest {

        private static RequestSpecification specification;
        private static String STRING_RESPONSE = "{\"id\":1,\"cpf\":\"12345678900\",\"phone\":\"40028922\",\"username\":\"teste\",\"fullName\":\"teste\",\"password\":\"teste\"}";

        @Test
        @Order(0)
        void createUserTeste() {

                specification = new RequestSpecBuilder()
                                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_FRONT)
                                .setBasePath("/api/user/save")
                                .setPort(TestConfigs.SERVER_PORT)
                                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                                .build();

                final var response = given()
                                .spec(specification)
                                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                                .body(new UserRecord("12345678900", "40028922", "teste", "teste", "teste"))
                                .when().post().then()
                                .statusCode(201)
                                .extract().body().asString();

                assertNotNull(response);
                assertEquals(STRING_RESPONSE, response);
        }

        @Test
        @Order(1)
        void findUserByCpfTest() {
                specification = new RequestSpecBuilder()
                                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_FRONT)
                                .setBasePath("/api/user/find")
                                .setPort(TestConfigs.SERVER_PORT)
                                .addQueryParam("cpf", "12345678900")
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
                assertEquals(STRING_RESPONSE, response);
        }
}