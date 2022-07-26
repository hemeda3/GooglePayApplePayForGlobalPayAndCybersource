package ahmed.com.digitalpayment.payments.services;

import ahmed.com.digitalpayment.payments.models.*;
import com.global.api.entities.Transaction;
import com.global.api.entities.exceptions.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ahmed.com.digitalpayment.payments.IPaymentService;


import java.util.Map;

import java.util.*;

import com.cybersource.ws.client.*;
@Slf4j
@Component
public class CybersourcePayService implements IPaymentService {

    @Value("${digitalpayment.cybersource.merchantid}")
    private static   String merchantid ;

    private static Properties props = Utility.readProperties(new String[] { "cybs.properties" });
    public CybersourcePayService()  {

    }

    @Override
    public String capture(CybersourceCaptureRequest request) throws ApiException {
        return captureInternal(request);
    }


    @Override
    public String authorize( CybersourceAuthRequest billingInfo) throws ApiException {
        return this.authorizeInternal(billingInfo);
    }



    @Override
    public String refund(CybersourceRefundRequest captureRequest) throws ApiException {

       return this.refundInternal(captureRequest);
    }
    @Override
    public Transaction refund(GlobalpaymentsRequest request) throws ApiException {
        throw new UnsupportedOperationException();
    }


    @Override
    public String voidCancelReverseAuthorization(CybersourceReverseRequest cybersourceReverseRequest) throws ApiException {
        return this.voidCancelReverseAuthorizationInternal(cybersourceReverseRequest);
    }

    @Override
    public Transaction voidCancelReverseAuthorization(GlobalpaymentsRequest request) throws ApiException {
        throw new UnsupportedOperationException();

    }

    @Override
    public Transaction authorize(GlobalpaymentsRequest request) throws ApiException {
        throw new UnsupportedOperationException("only for globalpayment");
    }
    @Override
    public Transaction capture(GlobalpaymentsRequest request) throws ApiException {
        throw new UnsupportedOperationException("only for globalpayment");
    }
    private   String captureInternal( CybersourceCaptureRequest request) throws ApiException {
        HashMap<String, String> params = new HashMap<>();

        String requestID;
        params.put("ccCaptureService_authRequestID", request.getAuthRequestID());
        params.put("merchantReferenceCode", UUID.randomUUID().toString());
        params.put("purchaseTotals_currency", request.getCurrency());
        params.put("item_0_unitPrice", request.getTotalAmount().toString());
        params.put("ccCaptureService_run", "true");

        requestID = performCybersourceCall(params);

        return requestID;
    }

    private   String refundInternal( CybersourceRefundRequest request) throws ApiException {
        HashMap<String, String> params = new HashMap<>();
  
        String requestID;
        params.put("ccCreditService_captureRequestID", request.getCaptureRequestID());
        params.put("ccCaptureService_authRequestID", request.getAuthRequestID());
        params.put("merchantReferenceCode", request.getMerchantTransactionCode());
        params.put("purchaseTotals_currency", request.getCurrency());
        params.put("item_0_unitPrice", request.getTotalAmount().toString());
        params.put("ccCaptureService_run", "true");
        requestID = performCybersourceCall(params);

        return requestID;
    }

    private String performCybersourceCall(HashMap<String, String> params) throws ApiException {
        String requestID;
        try {
            displayMap("CREDIT CARD AUTHORIZATION REQUEST:", params);

            // run transaction now
            Map<String, String> reply = Client.runTransaction(params, props);

            displayMap("CREDIT CARD AUTHORIZATION REPLY:", reply);

            // if the authorization was successful, obtain the request id
            // for the follow-on capture later.
            String decision = (String) reply.get("decision");
            if ("ACCEPT".equalsIgnoreCase(decision)) {
                requestID = (String) reply.get("requestID");
            } else {
                throw  new ApiException("decision is not accepted: "+decision);

            }
        } catch (Exception e) {

            throw  new ApiException(e.getMessage(), e);
        }
        return requestID;
    }

    private String authorizeInternal(CybersourceAuthRequest authRequest) throws ApiException {
        String merchantReferenceCode ="123";
        log.info(" ref code : "+ merchantReferenceCode);
        String requestID;

        HashMap<String, String> request = new HashMap<>();

        request.put( "ccAuthService_run", "true");
        request.put( "merchantID", merchantid);
        request.put( "merchantReferenceCode", merchantReferenceCode);
        request.put( "billTo_firstName",authRequest.getFirstName());
        request.put( "billTo_lastName", authRequest.getLastName() );
        request.put( "billTo_street1", authRequest.getStreet1() );
        request.put( "billTo_city", authRequest.getCity());
        request.put( "billTo_state", authRequest.getState());
        request.put( "billTo_postalCode", authRequest.getPostalCode());
        request.put( "billTo_country", authRequest.getCountry());
        request.put( "billTo_email", authRequest.getEmail() );

        request.put( "purchaseTotals_currency", authRequest.getPurchaseTotals_currency() );
        request.put( "purchaseTotals_grandTotalAmount", authRequest.getPurchaseTotals_grandTotalAmount() );
        request.put( "encryptedPayment_data", authRequest.getToken());

        if( authRequest.getWalletType() == WalletType.GOOGLE ) {
            request.put("paymentSolution", "012");
        } else if( authRequest.getWalletType() == WalletType.APPLE ) {
            request.put("paymentSolution", "001");
        }
        request.put("card_cardType", CardTypeEnum.
                valueOf(authRequest.getCreditCardType().toLowerCase()).getStringValue());

        requestID = performCybersourceCall(request);

        return requestID;
    }


    public String voidCancelReverseAuthorizationInternal(CybersourceReverseRequest cybersourceReverseRequest) throws ApiException {

        String requestID;

        HashMap<String, String> params = new HashMap<>();

        params.put("ccAuthReversalService_run", "true");
        params.put("merchantID", merchantid);

        params.put("ccCaptureService_authRequestID", cybersourceReverseRequest.getAuthRequestID());
        params.put("merchantReferenceCode", UUID.randomUUID().toString());
        params.put("purchaseTotals_currency", cybersourceReverseRequest.getCurrency());
        params.put( "purchaseTotals_grandTotalAmount", cybersourceReverseRequest.getTotalAmount().toString() );
        params.put("item_0_unitPrice", cybersourceReverseRequest.getTotalAmount().toString());

        requestID = performCybersourceCall(params);

        return requestID;
    }



    /**
     * Displays the content of the Map object.
     *
     * @param header	Header text.
     */
    private  void displayMap( String header, Map mapraw ) {

        log.info( header );

        TreeMap<String, String> map = new TreeMap<>(mapraw);
        StringBuffer dest = new StringBuffer();

        if (map != null && !map.isEmpty()) {
            Iterator iter = map.keySet().iterator();
            String key, val;

            while (iter.hasNext()) {
                key = (String) iter.next();
                val = (String) map.get( key );
                dest.append( key + "=" + val + "\n" );
            }
        }

        log.info( dest.toString() );
    }

}