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

import com.fiap.restaurant.review.application.records.review.PostReviewRecord;
import com.fiap.restaurant.review.infra.models.AddressModel;
import com.fiap.restaurant.review.infra.models.RestaurantModel;
import com.fiap.restaurant.review.infra.models.UserModel;
import com.fiap.restaurant.review.infra.repositories.AddressRepository;
import com.fiap.restaurant.review.infra.repositories.RestaurantRepository;
import com.fiap.restaurant.review.infra.repositories.UserRepository;
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
public class ReviewTestIntegration extends AbstractIntegrationTest {

    private static RequestSpecification specification;

    private Long userId;
    private Long restaurantId;

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;

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
        restaurant.setCnpj("71485296378451");
        restaurant.setPhone("40028922");
        restaurant.setFoodType("teste");
        restaurant.setOpenHour(LocalTime.of(0, 0, 0));
        restaurant.setCloseHour(LocalTime.of(0, 0, 0));
        restaurant.setAlwaysOpen(true);
        restaurant.setTotalCapacity(1);
        restaurant.setTotalGrade(1);
        restaurant.setAddress(address);
        restaurant = restaurantRepository.save(restaurant);

        var user = new UserModel();
        user.setCpf("77788899944");
        user.setPhone("40028911");
        user.setUsername("teste2");
        user.setFullName("teste2");
        user.setPassword("teste2");
        userRepository.save(user);

        userId = user.getId();
        restaurantId = restaurant.getId();
    }

    @Test
    @Order(0)
    void createReviewTeste() {

        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_FRONT)
                .setBasePath("/api/review/post")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        final var response = given()
                .spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(new PostReviewRecord(1, "teste teste teste teste", userId, restaurantId))
                .when().post().then()
                .statusCode(200)
                .extract().body().asString();

        assertNotNull(response);
    }

}
