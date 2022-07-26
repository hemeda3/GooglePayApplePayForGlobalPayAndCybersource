package performance;


import io.gatling.javaapi.core.Session;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;

import static performance.SystemPropertiesUtil.*;

@Slf4j
public final class PerfTestConfig {
    public static final String BASE_URL = getAsStringOrElse("baseUrl", "http://localhost:8080/digitalwallet");
    public static final double REQUEST_PER_SECOND = getAsDoubleOrElse("requestPerSecond", 1);
    public static final long DURATION_MIN = getAsIntOrElse("durationMin", 1);
    public static final int P95_RESPONSE_TIME_MS = getAsIntOrElse("p95ResponseTimeMs", 100000);
    public static String authReqBody = "{\n" +
            "        \"country\": \"US\",\n" +
            "        \"firstName\": \"US\",\n" +
            "        \"lastName\": \"US\",\n" +
            "        \"postalCode\": \"94043\",\n" +
            "        \"state\": \"CA\",\n" +
            "        \"city\": \"Mountain View\",\n" +
            "        \"street1\": \"1600 Amphitheatre Parkway\",\n" +
            "        \"region\": \"CA\",\n" +
            "        \"purchaseTotals_currency\": \"USD\",\n" +
            "        \"purchaseTotals_grandTotalAmount\": \"10\",\n" +
            "    \"email\":\"ahmed@gmail.com\",\n" +
            "        \"creditCardType\": \"VISA\",\n" +
            "        \"walletType\": \"GOOGLE\",\n" +
            "        \"token\": \"eyJzaWduYXR1cmUiOiJNRVlDSVFDZHlKWGI0ZVFHM09WOHc0SzNBZ1V3ZFF3T3hFM3k4blFEZWtpemcycHA1d0loQUxBUmpNWEYyZlVaNnhtVkN3Vnd2QW9Cb2wyZGlnNHhLK0FKUG1kWEZJMngiLCJwcm90b2NvbFZlcnNpb24iOiJFQ3YxIiwic2lnbmVkTWVzc2FnZSI6IntcImVuY3J5cHRlZE1lc3NhZ2VcIjpcIjlJU0tBSlBpSzlCUFJsa0VsVjVabmlqU1RTWnRVZUpGWlRVdzFMZHhHVzhmT20rVHlWRnNPc3FsNi90SitwYXRXZ1JtdUFrYnpibWw0UlV6RHpnYTl2L1dteWdOYXhMcFh5dlZaTlFmVGJiYkxHRmdySHdTcHFQSnpiRXhNQzg1cFJhcG9EZzdGQTJiM0ZTM3RwZ2ZHZEZpSlFjMTNxejQwT1owd0ZNdXNWMU5UT0lHTmxZUERUbEptSmIrZWROaTZHb1paQzZqdmZxN1V2ZG1ld0dpQ1c3ellDS2UzREFWSFVCU0xjcnVRR2NOODZ0YTA4OXM5UC9nVnJMTjg1U2hvLzZBbEluSjN6MDRrMTBlY2RadEVnR0s4K2lYZCtnN1Y2YjNnR2F5WXczWUFHa0FoL0FpSFB6bk9EL04zd1NrUkx0ZkpJWERWWmhaNno1Mno2dlRIQjdXRmt0cjJRaFZIeSswOVZsNmhnWkEwMG83aXl1MXcweUJOTkNPeUVHcHM3eWtEb3A2Y0dHME9VUEZKTTZjZSt4TjgrL3BHeUl4eko0VDNLS1ZiRUdCNEd6akkwSldWVnh4ckpUYWJqSkdjWDI1clFUdGxmeHRiSkVuWFY3OEJCc0gvZ0lSSDRBNmlZYWhvdHp3RlVOY1RaSFI3R3VGR0Z4L2RxWmtReFltYXBGNmRYY1ZGVlVhYWp1dGlqUDh2THB6ZkxtK1poTUE4Nlp6WnlMMTFkcjhtN05VME9TVTlyRGRyZTJ0a1pXMWtoaEgvbTR4U2dcXHUwMDNkXFx1MDAzZFwiLFwiZXBoZW1lcmFsUHVibGljS2V5XCI6XCJCSnAwWFRETm9Yc1JnMUJKd1pyNGVLb3dGZkRCY0RRNHdVY3I0N25OWDBLUmtLUEpTZTJ0NDFvMUhFeTdhcEljbnRhMG54cXM4SmNWNmhLY1hOcGZlMThcXHUwMDNkXCIsXCJ0YWdcIjpcImNtVUo1RVdraEwyM1BjSXlHa3B4Tnh6dFZzV2dLajcrUG03Ym94cmFMcDBcXHUwMDNkXCJ9In0=\"\n" +
            "    }";

    public static String captureRequest (String authRequestID) {


        log.info("captureRequestcaptureRequestcaptureRequest1");
        log.info(authRequestID);
        log.info("captureRequestcaptureRequestcaptureRequest-end");

        return " {\n" +
                "    \"authRequestID\": \""+authRequestID+"\",\n" +
                "    \"currency\": \"USD\",\n" +
                "    \"totalAmount\": \"1.0\"\n" +
                " }";
    }
    public static String refundRequest (String authRequestID, String captureID) {


        log.info("refundRequest");
        log.info(authRequestID);
        log.info("refundRequest-end");

        return "{\n" +
                "    \"authRequestID\": \"6586185243546345503007\",\n" +
                "    \"captureID\": \"6586189892766623603011\",\n" +
                "    \"currency\": \"USD\",\n" +
                "    \"totalAmount\": \"1.0\"\n" +
                " }";

    }

    public static String reverseRequest (String authRequestID) {


        log.info("reverseRequest");
        log.info(authRequestID);
        log.info("reverseRequest-end");

        return " {\n" +
                "    \"authRequestID\": \""+authRequestID+"\",\n" +
                "    \"currency\": \"USD\",\n" +
                "    \"totalAmount\": \"10\"\n" +
                " }";
    }


    public static Function<Session, String> requestBodyUpdated = (session) -> {

        String req = captureRequest(session.getString("authRequestID"));;
        log.info("requestBodyUpdated");
        log.info(req);
        log.info("requestBodyUpdated ");

        return req;
    };

    public static Function<Session, String> buildReverseAuthorzeRequest = (session) -> {

        String req = reverseRequest(session.getString("authRequestID"));;
        log.info("buildReverseAuthorzeRequest");
        log.info(req);
        log.info("buildReverseAuthorzeRequest ");

        return req;
    };

    public static Function<Session, String> buildRefundAuthorzeRequest = (session) -> {

        String req = refundRequest(session.getString("authRequestID"), session.getString("captureRequestid"));;
        log.info("buildReverseAuthorzeRequest");
        log.info(req);
        log.info("buildReverseAuthorzeRequest ");

        return req;
    };
}