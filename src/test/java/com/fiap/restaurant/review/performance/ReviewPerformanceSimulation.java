package com.fiap.restaurant.review.performance;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import lombok.NoArgsConstructor;

import java.util.concurrent.ThreadLocalRandom;
import java.util.List;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

@NoArgsConstructor
public class ReviewPerformanceSimulation {
    String getRandomUserId() {
        List<String> userIds = UserPerformanceSimulation.savedUserIds;
        if (userIds.isEmpty()) {
            throw new RuntimeException("No User IDs available.");
        }
        int randomIndex = ThreadLocalRandom.current().nextInt(0, userIds.size());
        return userIds.get(randomIndex);
    }

    String getRandomRestaurantId() {
        List<String> restaurantIds = RestaurantPerformanceSimulation.savedRestaurantIds;
        if (restaurantIds.isEmpty()) {
            throw new RuntimeException("No Restaurant IDs available.");
        }
        int randomIndex = ThreadLocalRandom.current().nextInt(0, restaurantIds.size());
        return restaurantIds.get(randomIndex);
    }

    public ScenarioBuilder execute(HttpProtocolBuilder httpProtocol){

        

        ChainBuilder postReviewRequest = exec(session -> {
            String userId = getRandomUserId();
            String restaurantId = getRandomRestaurantId();
            String reviewBody = """
            {
                "grade": %d,
                "comment": "Great restaurant!",
                "userId": %s,
                "restaurantId": %s
            }
            """.formatted(ThreadLocalRandom.current().nextInt(1, 6), userId, restaurantId);

            return session.set("reviewBody", reviewBody);
        }).exec(http("Post Review")
            .post("/api/review/post")
            .body(StringBody(session -> session.getString("reviewBody")))
            .check(status().is(200))
        );

        ScenarioBuilder scn = scenario("Post Review Scenario")
            .exec(postReviewRequest);

        return scn;
    }
}
