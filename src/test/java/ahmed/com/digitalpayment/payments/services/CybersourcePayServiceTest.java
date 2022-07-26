package ahmed.com.digitalpayment.payments.services;

import ahmed.com.digitalpayment.payments.models.*;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class CybersourcePayServiceTest {

    Locale locale = Locale.US;
    Currency currency = Currency.getInstance(locale);
    String token = "eyJzaWduYXR1cmUiOiJNRU1DSHlhdE03elF1NEFHcUhDRXpFSk5CSm1vZzZDSHZvRkhZZDUxUEZ4eDl4d0NJR3dUSUNlaGZzMHRsUHhBdzlTR1Q5c0FXYnk1b0t1MlVGZkdTeW5lM1ZxdyIsInByb3RvY29sVmVyc2lvbiI6IkVDdjEiLCJzaWduZWRNZXNzYWdlIjoie1wiZW5jcnlwdGVkTWVzc2FnZVwiOlwieDdEcmRDSWpHS0ZQbXB5T1JjMVZZNmcvcE9BRnhnZWZ5SDduZTJEeXB3YXlweko4OE8ySEsxODVveEk1T1NXcXlXYjRmeVovcDhydGpveEZYRTdadGt6STlaRDMzcWRFYkdmd3gxTzJ3TnpBNEhUZ3lDSkoxUFJxTVVSbzN1UWhWSzNzUVNRdnhxNzg3UkNLMnJYc2xobUdCbmVoTTU4RUl0Rlp1d1pXMFI5c1JyTTlHQjhMUzdtZks4WmV6cFBNYktUMWdHaWRmZHVUTDRUeEpxb2hpVU5RbTVYN0RtanpsWXNTTEVpVU4zRExCYUt6Slhod3RsaldkSHF5ZUhEcWh5blIrazd2WDFoSm1xZVVxNHBuL1BqbHJVSG9lR3JZL3piS0pQTlhUekVtYUlMNXY0ZlBGZ29mdzhnZytKRzZyMDk5WGNyQjVCSmlXdlZYZU9USFRjZlE5Vjh5a2k0V0NPNklvb2FsRDFHMVdBWVpKVlBZSlFlUVpkUmRJeDBHTU9IOW9wZTQxUTg4Qk5OV0NTSU9ZVXJxako1Q283cTNnVklZSk1XU1VlN1I0MnlWdDhCdjIwRjRiVlhtTXJOVzdhbE94RzJNNk0xUGROL1dEcVZ3amhNcnlZb1NZaTQyUzBtOXd3eVM4MXplVkJjRUliYXlMdHAvcnNpa2ZYUzVQS3ZCSDIvSmw2cmgrWnVSSmtCdFJDQmVMSDZKaGpDQUgzRUYwbXlucHd3anp4NGIrWktrUlJzeXVYMWJ1WWp5ZWt0ZVhMK0ErQVxcdTAwM2RcXHUwMDNkXCIsXCJlcGhlbWVyYWxQdWJsaWNLZXlcIjpcIkJDVWQydGdpZGk1NDRHYWxVS1BsV052N3RKRFRjd3BGemZTMHQ0UUpZbG9hY3VsTjQxbWljYXA0RFdUeWgyM3pLaVoyRmlXTFE3Y1A2TVd0UGFaV0hTa1xcdTAwM2RcIixcInRhZ1wiOlwiNThmUmR4LzlCYWJUazJwL3BrU1g2UDdHeDkvSXdMUDNsSkl2TTJVaWkzc1xcdTAwM2RcIn0ifQ==";
            //"eyJzaWduYXR1cmUiOiJNRVlDSVFDZHlKWGI0ZVFHM09WOHc0SzNBZ1V3ZFF3T3hFM3k4blFEZWtpemcycHA1d0loQUxBUmpNWEYyZlVaNnhtVkN3Vnd2QW9Cb2wyZGlnNHhLK0FKUG1kWEZJMngiLCJwcm90b2NvbFZlcnNpb24iOiJFQ3YxIiwic2lnbmVkTWVzc2FnZSI6IntcImVuY3J5cHRlZE1lc3NhZ2VcIjpcIjlJU0tBSlBpSzlCUFJsa0VsVjVabmlqU1RTWnRVZUpGWlRVdzFMZHhHVzhmT20rVHlWRnNPc3FsNi90SitwYXRXZ1JtdUFrYnpibWw0UlV6RHpnYTl2L1dteWdOYXhMcFh5dlZaTlFmVGJiYkxHRmdySHdTcHFQSnpiRXhNQzg1cFJhcG9EZzdGQTJiM0ZTM3RwZ2ZHZEZpSlFjMTNxejQwT1owd0ZNdXNWMU5UT0lHTmxZUERUbEptSmIrZWROaTZHb1paQzZqdmZxN1V2ZG1ld0dpQ1c3ellDS2UzREFWSFVCU0xjcnVRR2NOODZ0YTA4OXM5UC9nVnJMTjg1U2hvLzZBbEluSjN6MDRrMTBlY2RadEVnR0s4K2lYZCtnN1Y2YjNnR2F5WXczWUFHa0FoL0FpSFB6bk9EL04zd1NrUkx0ZkpJWERWWmhaNno1Mno2dlRIQjdXRmt0cjJRaFZIeSswOVZsNmhnWkEwMG83aXl1MXcweUJOTkNPeUVHcHM3eWtEb3A2Y0dHME9VUEZKTTZjZSt4TjgrL3BHeUl4eko0VDNLS1ZiRUdCNEd6akkwSldWVnh4ckpUYWJqSkdjWDI1clFUdGxmeHRiSkVuWFY3OEJCc0gvZ0lSSDRBNmlZYWhvdHp3RlVOY1RaSFI3R3VGR0Z4L2RxWmtReFltYXBGNmRYY1ZGVlVhYWp1dGlqUDh2THB6ZkxtK1poTUE4Nlp6WnlMMTFkcjhtN05VME9TVTlyRGRyZTJ0a1pXMWtoaEgvbTR4U2dcXHUwMDNkXFx1MDAzZFwiLFwiZXBoZW1lcmFsUHVibGljS2V5XCI6XCJCSnAwWFRETm9Yc1JnMUJKd1pyNGVLb3dGZkRCY0RRNHdVY3I0N25OWDBLUmtLUEpTZTJ0NDFvMUhFeTdhcEljbnRhMG54cXM4SmNWNmhLY1hOcGZlMThcXHUwMDNkXCIsXCJ0YWdcIjpcImNtVUo1RVdraEwyM1BjSXlHa3B4Tnh6dFZzV2dLajcrUG03Ym94cmFMcDBcXHUwMDNkXCJ9In0=";
    CybersourceAuthRequest cybersourceAuthRequest = null;


    private CybersourcePayService cybersourcePayService;

    @BeforeEach
    void setUp() {
        cybersourcePayService = new CybersourcePayService();
        cybersourceAuthRequest = getBillingInfo(currency, token);

    }


    @Test
    void testA_Authorize_Success() throws Exception {

        String requestid = cybersourcePayService.authorize( cybersourceAuthRequest);
        assertThatNoException();
        assertNotNull(requestid);

    }

    @Test
    void testB_ReverseAuthorize_Success() throws Exception {

        String authRequestid = cybersourcePayService.authorize( cybersourceAuthRequest);
        assertThatNoException();
        assertNotNull(authRequestid);

        CybersourceReverseRequest cybersourceReverseRequest =
                new CybersourceReverseRequest( authRequestid, currency.getCurrencyCode(), new BigDecimal("1"), null,UUID.randomUUID().toString());

        String reverseAuthorization = cybersourcePayService.voidCancelReverseAuthorization(cybersourceReverseRequest);
        assertThatNoException();
        assertNotNull(reverseAuthorization);


    }

    @Test
    void testC_CaptureAfterAuthorize_Success() throws Exception {

        String authRequestid = cybersourcePayService.authorize( cybersourceAuthRequest);

        CybersourceCaptureRequest captureRequest =
                new CybersourceCaptureRequest(UUID.randomUUID().toString(), authRequestid, currency.getCurrencyCode(), new BigDecimal("1"), "");

        String captureRequestid = cybersourcePayService.capture(captureRequest);
        assertThatNoException();
        assertNotNull(authRequestid);
        assertNotNull(captureRequestid);

    }

    @Test
    void testD_FollowOnRefundAfterCapture_Success() throws Exception {

        String authRequestid = cybersourcePayService.authorize( cybersourceAuthRequest);

        CybersourceCaptureRequest captureRequest =
                new CybersourceCaptureRequest(UUID.randomUUID().toString(), authRequestid, currency.getCurrencyCode(), new BigDecimal("1"), "");

        String captureRequestid = cybersourcePayService.capture(captureRequest);

        CybersourceRefundRequest refundRequest =
                new CybersourceRefundRequest( captureRequestid,authRequestid, currency.getCurrencyCode(),  new BigDecimal("1"), null,UUID.randomUUID().toString());

        String refundRequestId = cybersourcePayService.refund(refundRequest);
        assertThatNoException();
        assertNotNull(authRequestid);
        assertNotNull(captureRequestid);
        assertNotNull(refundRequestId);

    }
    private CybersourceAuthRequest getBillingInfo(Currency currency, String token) {
        final CybersourceAuthRequest cybersourceAuthRequest = new CybersourceAuthRequest();
        cybersourceAuthRequest.setCity("Mountain View");
        cybersourceAuthRequest.setCountry("US");
        cybersourceAuthRequest.setFirstName("Mock");
        cybersourceAuthRequest.setLastName("Name");
        cybersourceAuthRequest.setPostalCode("94043");
        cybersourceAuthRequest.setStreet1("1600 Amphitheatre Parkway");
        cybersourceAuthRequest.setRegion("CA");
        cybersourceAuthRequest.setState("CA");
        cybersourceAuthRequest.setEmail("mock@name.com");

        cybersourceAuthRequest.setToken(token);

        cybersourceAuthRequest.setWalletType(WalletType.GOOGLE);
        cybersourceAuthRequest.setCreditCardType(CreditCardType.VISA.toString());
        cybersourceAuthRequest.setPurchaseTotals_currency(currency.getCurrencyCode());
        cybersourceAuthRequest.setPurchaseTotals_grandTotalAmount("1.0");
        return cybersourceAuthRequest;
    }

}
