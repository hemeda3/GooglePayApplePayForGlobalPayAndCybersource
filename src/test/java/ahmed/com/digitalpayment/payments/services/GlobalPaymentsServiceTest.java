package ahmed.com.digitalpayment.payments.services;

import ahmed.com.digitalpayment.payments.models.GlobalpaymentsRequest;
import ahmed.com.digitalpayment.payments.models.WalletType;
import com.global.api.entities.Transaction;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Currency;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class GlobalPaymentsServiceTest {

    private GlobalPaymentsService globalPaymentsServiceUnderTest;
    Locale locale = Locale.US;
    java.util.Currency currency = Currency.getInstance(locale);

    String token_decoded = "{\"signature\":\"MEYCIQDoh2fszvz0HYbFBkbJrNa0HX21XoufyI26jCI460VXDAIhAMLEM8XgurhpJwWndDLxDc0YPwDfcq1Mykc5QhoSg2Lr\",\"protocolVersion\":\"ECv1\",\"signedMessage\":\"{\\\"encryptedMessage\\\":\\\"IMBNg2a4BriZoTC4bu2RSaYPoK7elSND1e4qOyaoyQGlHa8DYwcJ1rynCuQqlonQHgFGQX7FdIGALbrnQqjZM82H+gOOCSpnikKzQPlQq9XkVTCRlea67VMWmM5Gcfw0kanvHKeWsUowCUQ6kkcaN9ta+ioWbmVNC2h/5EdlrTh4g3ze3fDx5D68fsJBCsxLbnwJ1Rs7z0HKGNi675htjZs59amWtwFGK57Xw57nh8btDz/YuSomNE6h+9sGIfJ7NNRRz80+Gg1f8GfGGqL3n0dHydWG1ofH5XcGegws970UAf5o1RcLEGavq+//lTljaVAwHgMQmXi23pNnrQpN69/BzPNsxsT3O9JP6+ETDEt6YWOytB34u9klfGe56/bHYGH/xWy/DrlR96FP0q5sKN0hhJYCBGdPW8ou0vuUmdJdzOZ4oZC/hqOvusDUtWr8ozefZ447tMWEz5+0CFK5QA\\\\u003d\\\\u003d\\\",\\\"ephemeralPublicKey\\\":\\\"BI17eCLEpGwx7W8sjg/qfsn9ys8bh7SNAP2rTnCF14jzgUSij3hRUOdm07RLICBqwcoysmcun74mrlICMdaY9FM\\\\u003d\\\",\\\"tag\\\":\\\"mxrFspsqqj4B4JyH6SriOhHXKcQOy+mDgAD6r5BSEZA\\\\u003d\\\"}\"}";
    String token = "eyJzaWduYXR1cmUiOiJNRVFDSUJJckx6ZG1JSUlXMDhzWlZYMnZDS0l1a2hnMmNpSVNpb2QyYVJhMUpPYW9BaUFyMVN0VFlWRUVQYkxWV1p1NERvZjJhU0oyM0tGN1ZqaStPblRIc3NSdFpnXHUwMDNkXHUwMDNkIiwicHJvdG9jb2xWZXJzaW9uIjoiRUN2MSIsInNpZ25lZE1lc3NhZ2UiOiJ7XCJlbmNyeXB0ZWRNZXNzYWdlXCI6XCIrM1MrTWlFUVRLZ3FNUGt2UUJnUjFIRHp1OXFDSTNDbkU2aEl0MHlWUmVpSW5TazgxOXFMRWVZMWptbDVRRWdNUk94R2FqVXdLL2tFWXhjc3lCQkd0c2s4SFV4aDR3bGlScHhla2NnTHVCeHorRFY3WmNWUlRCTkhuRnFQTnNtSm1yL2JJd2hGZitWMTRvN2xyTjFmL3Jpc3JpeWJqMk1tc3RUWjdzTkwraDZZNXp4ZndsYk1yaU9HOEdTbnU2Qjl4blJoS2MwbVRPOHBoWjRsZEY2MXhXVVNyTXJWdTltM05NNTVxOUZIdmYrN3BnYWFpR0FxTXZVMlBQd041KzVrU2ZPTmZkMksrekNHQldHL3JNRUpsQzZwTm1vZW1mb2FmYWwvL1FxcmJLbFU2dTQ2ci8zcFRrdlYxbXhSQ3ZIVHp3KzBoV29uamNvT21SbnVzSXFrcU1iOUIwaWlaZTlKSWhLd0tnd0VFNFBhOUxPWTZ2Y1k4VGNXcGIvZmMvV3crYlNQMUlrQTl4b3pocjFPcThsU2FKandkbTl1SS8wb1ZreGtuRDZQcXREdml5RzYvSU5leVVuRnZpN2E3b2J3RzRtZTUwSHZuZWtNWkoyYVdlVUk1RjBVNFEyaENTTmJPNHpFSjNPTFhSZ3ZVaEdOdmgxaHRXVXljVnhPMlJQdHkzT0dtUzZKSGVCbThzM2xuQ1dtK1NOelhNekY1Mkp1WlloTjh2VDNRYzR5cEUwTUZRb2Ryc2h4Z05tTTRIcDFxdkhDZ0YrUjlsbmVrOHBqbUp0S1VDanZ2ckVcXHUwMDNkXCIsXCJlcGhlbWVyYWxQdWJsaWNLZXlcIjpcIkJKeFE2REpjVWNLWitmeWgzc25lbVBNQnFPb2htbDdQSkJlTEVsMjhHZmNjRHlCUEFHeVBqMXZOYUZzOEs5TmxGUEluY2NCSFQ5WWlNZ0FNOC9kYU9SZ1xcdTAwM2RcIixcInRhZ1wiOlwiUEl1cjgyY2Y4NHcvaFNhbFBZWEI4YVJCSE9PcFMrU0lEbVFYWGN6aGxCMFxcdTAwM2RcIn0ifQ==";
            //"eyJzaWduYXR1cmUiOiJNRVVDSUd1QzlxYzNjc0dPKzl1eWUzdDQxQkxyaGdocTNtTVp6SStlUk5aR083RjRBaUVBenQyTVNZR1FtYXNYR2p0R043ekZYcENweEtoeHZwbmo3NysweEtueUpGd1x1MDAzZCIsInByb3RvY29sVmVyc2lvbiI6IkVDdjEiLCJzaWduZWRNZXNzYWdlIjoie1wiZW5jcnlwdGVkTWVzc2FnZVwiOlwiVjJ3YnRtYWxSek0xMkp6ck1ZVzFmQmNEeE9reWNVZTFrTlUxU2lMUVo0NmFoUkFRRGVXVXJpdWVaZjUwUHlZRXRNa1RkVGZGSW5zQ3RROEhnNHFSRHpXOUNhVkVMRnI1WE1yYWwxOTFGZkhMUGhPSDc1WG5mSUFqb2h0ZXVrVDV1TUxvTFBJNWYzTE4vUlZUcnpwUktkRWRnb0Erb01NNDJDa05hYm9VeTNsdjJRaFdoRUJiVFhiN3FWcnd6UDV2MTVNd1o3dHFiL1cxaThDcTFSUXZVT1BlMkloaWVIejFHdjYvaXp1KzZIeVZwWHJLRjFONXgvemNPZlBJaERxRGViZWpaWURlZEFvd2Q0Ry9qNHF2eG4wWUJFc1VENkN1MUJtWlpBRERwdjJ1ZUlybjdKeC9ON3VvVHQ5NHQ5WnV6N3ZvZEhReit6THVTQkc0cWs4bHNORTlOUDBSNmlKWnBibWQ3b2huZk96TVJNZjljcmVMa0xvKzM0S3pwNDJ2UGFvYW5EeXZmWTREbUduNmxUdHBheEcwMkVZWHdlK2FIWTRnYURuT0czNU1nekZsclhEM3hvbWc5NXhkM2p1WG9wWjBpajFiTDNwR2lDUGwvWkphTXZqVFp2M3NsNExEZittby9YYUNzdW05Q2V4SVQvZjF0SDlKQjM2R3FBemRTNUw0TVNLdmtVUU51Z3haSlJTdU9SS2pBMmptQjl0Q21ubmJSaWYvWWM5NTF1U0VrMEhvM3ZES3U3NCtuQkU4MVFadUk0MkFNRkdtOGlsblJGQnpqRERsc20wXFx1MDAzZFwiLFwiZXBoZW1lcmFsUHVibGljS2V5XCI6XCJCQjJPaGNNNVlhYXk2Y0VGSFllRlpDWkZNYlBub1h2d1hKMHRSRExGTHBZdnNWT3dhRS9USk9nUy9xZHZWSXBVRXhMN2t5SzhDMzRtYzB6WHZ2RVRQRVFcXHUwMDNkXCIsXCJ0YWdcIjpcIk03OGpmVnZYKzF1dUNOeHM4enJ5dWhpTnVIN1l2L1RUT1hMV1NRZ2hvQ29cXHUwMDNkXCJ9In0=";

    @Value("${digitalpayment.globalpayments.merchantID}")
    private String merchantID ;

    @Value("${digitalpayment.globalpayments.accountId:internet}")
    String accountId ;

    @Value("${digitalpayment.globalpayments.sharedSecret}")
    String sharedSecret ;

    @Value("${digitalpayment.globalpayments.refundSecret}")
    String refundSecret ;

    @Value("${digitalpayment.globalpayments.rebateSecret}")
    String rebateSecret ;
    @BeforeEach
    void setUp() throws Exception {
        System.out.println(merchantID);
        System.out.println(merchantID);
        System.out.println(merchantID);
        globalPaymentsServiceUnderTest = new GlobalPaymentsService(merchantID, accountId, sharedSecret, refundSecret, rebateSecret);
    }

    @Test
    void testAuthorize_success() throws Exception {
        // Setup
        final GlobalpaymentsRequest request = GlobalpaymentsRequest.builder()
                .amount("10")
                .currency(currency.getCurrencyCode())
                .digitalPaymentToken(token)
                .walletType(WalletType.GOOGLE)
                .build();

        // Run the test
        final Transaction result = globalPaymentsServiceUnderTest.authorize(request);
        // Verify the results
        Assert.assertThat(result.getResponseMessage(), CoreMatchers.containsString("[ test system ] Authorised"));

    }
    @Test
    void testReverseAuthorize_success() throws Exception {
         final GlobalpaymentsRequest request_auth = GlobalpaymentsRequest.builder()
                .amount("10")
                .currency(currency.getCurrencyCode())
                .digitalPaymentToken(token)
                .walletType(WalletType.GOOGLE).build();
         final Transaction auth_result = globalPaymentsServiceUnderTest.authorize(request_auth);

        final GlobalpaymentsRequest request_reverse = GlobalpaymentsRequest.builder()
                .transactionId(auth_result.getTransactionId())
                .orderId(auth_result.getOrderId()).build();
        final Transaction result_reverse = globalPaymentsServiceUnderTest.voidCancelReverseAuthorization(request_reverse);

        Assert.assertThat(result_reverse.getResponseMessage(), CoreMatchers.containsString("Voided Successfully"));

    }
    @Test
    void testCapture_Success() throws Exception {
        final GlobalpaymentsRequest request_auth = GlobalpaymentsRequest.builder()
                .amount("10")
                .currency(currency.getCurrencyCode())
                .digitalPaymentToken(token)
                .walletType(WalletType.GOOGLE).build();
        final Transaction auth_result = globalPaymentsServiceUnderTest.authorize(request_auth);


        final GlobalpaymentsRequest request_capture = GlobalpaymentsRequest.builder()
                .transactionId(auth_result.getTransactionId())
                .orderId(auth_result.getOrderId()).build();

        final Transaction capture_result = globalPaymentsServiceUnderTest.capture(request_capture);

        Assert.assertThat(capture_result.getResponseMessage(), CoreMatchers.containsString("Settled Successfully"));

    }

    @Test
    void testRefund_Success() throws Exception {
        final GlobalpaymentsRequest request_auth = GlobalpaymentsRequest.builder()
                .amount("10")
                .currency(currency.getCurrencyCode())
                .digitalPaymentToken(token)
                .walletType(WalletType.GOOGLE).build();
        final Transaction auth_result = globalPaymentsServiceUnderTest.authorize(request_auth);


        final GlobalpaymentsRequest request_capture = GlobalpaymentsRequest.builder()
                .transactionId(auth_result.getTransactionId())
                .orderId(auth_result.getOrderId()).build();

        final Transaction capture_result = globalPaymentsServiceUnderTest.capture(request_capture);

        final GlobalpaymentsRequest request_refund = GlobalpaymentsRequest.builder()
                .authCode(auth_result.getAuthorizationCode())
                .transactionId(auth_result.getTransactionId())
                .orderId(auth_result.getOrderId())
                .currency(request_auth.getCurrency())
                .amount(request_auth.getAmount())
                .build();
        final Transaction refund_result = globalPaymentsServiceUnderTest.refund(request_refund);

        Assert.assertThat(refund_result.getResponseMessage(), CoreMatchers.containsString("AUTH CODE:"));

    }

}
