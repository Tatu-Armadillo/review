package com.fiap.restaurant.review.performance;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

@NoArgsConstructor
public class UserPerformanceSimulation {

    public static List<String> savedCpfs = new ArrayList<>();
    public static List<String> savedUserIds = new ArrayList<>();

    private String generateRandomCPF() {
        StringBuilder cpf = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            int digit = ThreadLocalRandom.current().nextInt(0, 10);
            cpf.append(digit);
        }
        return cpf.toString();
    }

    public ScenarioBuilder execute(HttpProtocolBuilder httpProtocol) {

        ChainBuilder saveUserRequest = exec(session -> {
            String cpf = generateRandomCPF();
            String requestBody = """
            {
                "cpf": "%s",
                "phone": "%s",
                "username": "test_user_%s",
                "fullName": "Test User",
                "password": "password123"
            }
            """.formatted(cpf, cpf, cpf);
            savedCpfs.add(cpf); 
            return session.set("cpf", cpf)
                          .set("requestBody", requestBody);
        }).exec(http("Save User")
            .post("/api/user/save")
            .body(StringBody(session -> session.getString("requestBody")))
            .check(status().is(201))
            .check(jsonPath("$.id").saveAs("userId"))
        ).exec(session -> {
            String userId = session.getString("userId");
            savedUserIds.add(userId);
            return session;
        });

        ChainBuilder findUserRequest = exec(http("Find User by CPF")
            .get("/api/user/find")
            .queryParam("cpf", session -> session.getString("cpf"))
            .check(status().is(200))
        );
        ScenarioBuilder scn = scenario("Save and Find User Scenario")
            .exec(saveUserRequest)
            .pause(Duration.ofSeconds(1)) 
            .exec(findUserRequest);

        return scn;
    }
}
