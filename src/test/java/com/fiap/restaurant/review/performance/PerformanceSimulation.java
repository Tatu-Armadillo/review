package com.fiap.restaurant.review.performance;


import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;


public class PerformanceSimulation extends Simulation{

    private final HttpProtocolBuilder httpProtocol =
    http.baseUrl("http://localhost:9090")
        .header("Content-Type", "application/json");

    private String generateRandomCPF() {
        StringBuilder cpf = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            int digit = ThreadLocalRandom.current().nextInt(0, 10);
            cpf.append(digit);
        }
        return cpf.toString();
    }

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

        return session.set("requestBody", requestBody); 
    }).exec(http("Save User")
        .post("/api/user/save")
        .body(StringBody(session -> session.getString("requestBody")))
        .check(status().is(201))
    );
    
    ScenarioBuilder scn = scenario("Save User Scenario")
        .exec(saveUserRequest);
        {
            setUp(
                scn.injectOpen(rampUsersPerSec(1).to(10).during(Duration.ofSeconds(10)),
                constantUsersPerSec(10).during(Duration.ofSeconds(60)),
                rampUsersPerSec(10).to(1).during(Duration.ofSeconds(10)))) 
            .protocols(httpProtocol)
            .assertions(global().responseTime().max().lt(50));
        }

}
