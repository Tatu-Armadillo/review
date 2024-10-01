package com.fiap.restaurant.review.integration.test;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fiap.restaurant.review.application.records.booking.SimpleTableRecord;
import com.fiap.restaurant.review.infra.models.AddressModel;
import com.fiap.restaurant.review.infra.models.RestaurantModel;
import com.fiap.restaurant.review.infra.repositories.AddressRepository;
import com.fiap.restaurant.review.infra.repositories.RestaurantRepository;
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
public class TableTestIntegration extends AbstractIntegrationTest {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private AddressRepository addressRepository;
    private static RequestSpecification specification;

    @BeforeAll
    void setUp() {
        var address = new AddressModel();
        address.setCep("12345678");
        address.setPublicPlace("teste");
        address.setComplement("teste");
        address.setNeighborhood("teste");
        address.setCity("teste");
        address.setUfState("te");
        address.setLongitude(0.0);
        address.setLatitude(0.0);
        address = addressRepository.save(address);
        var restaurant = new RestaurantModel();
        restaurant.setName("teste");
        restaurant.setCnpj("2234567891011");
        restaurant.setPhone("40028922");
        restaurant.setFoodType("teste");
        restaurant.setOpenHour(LocalTime.of(0, 0, 0));
        restaurant.setCloseHour(LocalTime.of(0, 0, 0));
        restaurant.setAlwaysOpen(true);
        restaurant.setTotalCapacity(1);
        restaurant.setTotalGrade(1);
        restaurant.setAddress(address);
        restaurant = restaurantRepository.save(restaurant);
    }

    @Test
    @Order(0)
    void createTableTeste() {

        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_FRONT)
                .setBasePath("/api/table/save")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        final var response = given()
                .spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(new SimpleTableRecord(1, "2234567891011"))
                .when().post().then()
                .statusCode(200)
                .extract().body().asString();

        assertNotNull(response);
    }

    @Test
    @Order(1)
    void findAllTablesTeste() {

        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_FRONT)
                .setBasePath("/api/table/all")
                .setPort(TestConfigs.SERVER_PORT)
                .addQueryParam("cnpj", "2234567891011")
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
    }

}
