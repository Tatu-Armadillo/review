package com.fiap.restaurant.review.performance;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

@NoArgsConstructor
public class RestaurantPerformanceSimulation {

    public static List<String> savedCnpjs = new ArrayList<>();
    public static List<String> savedRestaurantIds = new ArrayList<>();

    private String generateRandomCNPJ() {
        StringBuilder cnpj = new StringBuilder();
        for (int i = 0; i < 14; i++) {
            int digit = ThreadLocalRandom.current().nextInt(0, 10);
            cnpj.append(digit);
        }
        return cnpj.toString();
    }

    private LocalTime generateRandomTime() {
        int hour = ThreadLocalRandom.current().nextInt(0, 24);
        int minute = ThreadLocalRandom.current().nextInt(0, 60);
        return LocalTime.of(hour, minute);
    }

    public ScenarioBuilder execute(HttpProtocolBuilder httpProtocol) {

        ChainBuilder saveRestaurantRequest = exec(session -> {
            String cnpj = generateRandomCNPJ();
            String requestBody = """
            {
                "name": "Restaurant_%s",
                "cnpj": "%s",
                "phone": "1234567890",
                "foodType": "Brazilian",
                "openHour": "%s",
                "closeHour": "%s",
                "alwaysOpen": false,
                "totalCapacity": 50,
                "totalGrade": 5,
                "address": {
                    "cep": "12345678",
                    "publicPlace": "Main St",
                    "complement": "Suite 1",
                    "neighborhood": "Downtown",
                    "city": "Sao Paulo",
                    "ufState": "SP",
                    "latitude": "-23.5505",
                    "longitude": "-46.6333"
                }
            }
            """.formatted(cnpj, cnpj, generateRandomTime(), generateRandomTime());

            savedCnpjs.add(cnpj);

            return session.set("cnpj", cnpj)
                          .set("requestBody", requestBody);
        }).exec(http("Save Restaurant")
            .post("/api/restaurant/save")
            .body(StringBody(session -> session.getString("requestBody")))
            .check(status().is(200))
            .check(jsonPath("$.id").saveAs("restaurantId"))
        ).exec(session -> {
            String restaurantId = session.getString("restaurantId");
            savedRestaurantIds.add(restaurantId);
            return session;
        });

        ScenarioBuilder scn = scenario("Save Restaurant Scenario")
            .exec(saveRestaurantRequest)
            .pause(Duration.ofSeconds(1)); 

        return scn;
    }
}
