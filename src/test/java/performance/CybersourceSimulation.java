package performance;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Session;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.function.Function;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;
import static performance.PerfTestConfig.*;

@Slf4j
public class CybersourceSimulation extends Simulation {

    HttpProtocolBuilder httpProtocol = http.baseUrl(BASE_URL)
            .header("Content-Type", "application/json")
            .check(status().is(200));
    static String authid = "";


    ScenarioBuilder authorizeOnlyScenario = scenario("Authorization+Reverse")
            .exec(http("authorizeOnlyScenario->authorize")
                    .post("/cybersource/authorize")
                    .body(StringBody(authReqBody))
                    .check(status().is(200))
                    .check(jsonPath("$..authCode").exists().saveAs("authRequestID"))

            )
            .exec(session -> {
                authid = session.getString("authRequestID");
                return session;
            });

    ScenarioBuilder reverseAfterAuthorizeScenario = scenario("Reverse+Authorization")
            .exec(http("reverseAfterAuthorizeScenario->authorize ")
                    .post("/cybersource/authorize")
                    .body(StringBody(authReqBody))
                    .check(status().is(200))
                    .check(jsonPath("$..authCode").exists().saveAs("authRequestID"))

            )
            .exec(session -> {
                authid = session.getString("authRequestID");
                return session;
            })
            .exec(http("reverseAfterAuthorizeScenario->reverse")
                    .post("/cybersource/reverseAuthorization")
                    .body(StringBody(buildReverseAuthorzeRequest))
                    .check(status().is(200))
                    .check(jsonPath("$..authCode").exists())

            ) .exec(session -> {
                authid = null;
                return session;
            });


    ScenarioBuilder captureAfterAuthorizeScenario = scenario("Capture+Authorize")
            .exec(http("captureAfterAuthorizeScenario->authorize")
                    .post("/cybersource/authorize")
                    .body(StringBody(authReqBody))
                    .check(status().is(200))
                    .check(jsonPath("$..authCode").exists().saveAs("authRequestID"))
            )
            .exec(session -> {
                authid = session.getString("authRequestID");
                return session;
            })
           .exec(
                http("reverseAfterAuthorizeScenario->capture")
                        .post("/cybersource/capture")

                        .body(StringBody(requestBodyUpdated))
                        .check(status().is(200))

            ).exec(session -> {
                authid = null;
                return session;
            });

    ScenarioBuilder refundAfterCaptureAfterAuthorizeScenario = scenario("Refund+Capture+Authorize")
            .exec(http("refundAfterCaptureAfterAuthorizeScenario->authorize")
                    .post("/cybersource/authorize")
                    .body(StringBody(authReqBody))
                    .check(status().is(200))
                     .check(jsonPath("$..authCode").exists().saveAs("authRequestID"))

            )
            .exec(session -> {
                authid = session.getString("authRequestID");
                return session;
            })
            .exec(
                    http("refundAfterCaptureAfterAuthorizeScenario->capture")
                            .post("/cybersource/capture")

                            .body(StringBody(requestBodyUpdated))
                            .check(status().is(200))
                            .check(jsonPath("$..captureID").exists().saveAs("captureRequestid"))


            ).exec(
            http("refundAfterCaptureAfterAuthorizeScenario->refund")
                            .post("/cybersource/refund")

                            .body(StringBody(buildRefundAuthorzeRequest))
            .check(status().is(200))

            ).exec(session -> {
                authid = null;
                return session;
            });



    {
        setUp(
                authorizeOnlyScenario.
                        injectOpen(constantUsersPerSec(REQUEST_PER_SECOND)
                                .during(Duration.ofSeconds(DURATION_MIN))),

                reverseAfterAuthorizeScenario.
                injectOpen(constantUsersPerSec(REQUEST_PER_SECOND)
                .during(Duration.ofSeconds(DURATION_MIN))),

                captureAfterAuthorizeScenario.
                        injectOpen(constantUsersPerSec(REQUEST_PER_SECOND)
                                .during(Duration.ofSeconds(DURATION_MIN))),

                refundAfterCaptureAfterAuthorizeScenario.
                        injectOpen(constantUsersPerSec(REQUEST_PER_SECOND)
                                .during(Duration.ofSeconds(DURATION_MIN)))

        )
                .protocols(httpProtocol)
                .assertions(
                        global().responseTime().percentile3().lt(P95_RESPONSE_TIME_MS),
                        global().requestsPerSec().lt(1000D),
                        global().successfulRequests().percent().gt(95.0));

    }


}