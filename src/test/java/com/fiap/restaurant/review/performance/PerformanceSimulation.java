package com.fiap.restaurant.review.performance;


// import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;
// import java.util.concurrent.ThreadLocalRandom;
import java.util.List;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
// import static io.gatling.javaapi.http.HttpDsl.status;


public class PerformanceSimulation extends Simulation{

    private final HttpProtocolBuilder httpProtocol =
    http.baseUrl("http://localhost:9090")
        .header("Content-Type", "application/json");

    UserPerformanceSimulation userPerformanceSimulation = new UserPerformanceSimulation();
    RestaurantPerformanceSimulation restaurantPerformanceSimulation = new RestaurantPerformanceSimulation();

    ScenarioBuilder userScn = userPerformanceSimulation.execute(httpProtocol);
    ScenarioBuilder restaruantScn = restaurantPerformanceSimulation.execute(httpProtocol);


        {
            setUp(
                userScn.injectOpen(rampUsersPerSec(1).to(10).during(Duration.ofSeconds(10)),
                constantUsersPerSec(10).during(Duration.ofSeconds(60)),
                rampUsersPerSec(10).to(1).during(Duration.ofSeconds(10))),
                restaruantScn.injectOpen(rampUsersPerSec(1).to(10).during(Duration.ofSeconds(10)),
                constantUsersPerSec(10).during(Duration.ofSeconds(60)),
                rampUsersPerSec(10).to(1).during(Duration.ofSeconds(10)))) 
            .protocols(httpProtocol)
            .assertions(global().responseTime().max().lt(200));
        }

    public List<String> getSavedCpfs() {
        return UserPerformanceSimulation.savedCpfs;
    }

    public List<String> getSavedCnpjs() {
        return RestaurantPerformanceSimulation.savedCnpjs;
    }
}
