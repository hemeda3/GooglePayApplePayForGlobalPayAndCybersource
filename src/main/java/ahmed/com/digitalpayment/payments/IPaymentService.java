package ahmed.com.digitalpayment.payments;

import ahmed.com.digitalpayment.payments.models.*;
import com.global.api.entities.Transaction;
import com.global.api.entities.exceptions.ApiException;

public interface IPaymentService {

    Transaction authorize(GlobalpaymentsRequest request) throws ApiException;
    String authorize(CybersourceAuthRequest cybersourceAuthRequest) throws ApiException;
    Transaction capture(GlobalpaymentsRequest request)  throws ApiException;
    String capture(CybersourceCaptureRequest request) throws ApiException;
    String refund(CybersourceRefundRequest captureRequest) throws ApiException;
    Transaction refund(GlobalpaymentsRequest request) throws ApiException;
    /**
     * Before a transaction is captured, it is possible to Void an Authorization,
     * Capture, Refund, or Credit request.
     * If the transaction was already placed in the next settlement file (or batch),
     * this will remove it. If using Delayed Capture, this will cancel the initial authorization.
     * If the transaction was settled or batched,
     * then it cannot be voided. This request requires the ID from the original transaction.
     *
     */
    String voidCancelReverseAuthorization(CybersourceReverseRequest cybersourceReverseRequest) throws ApiException;
    Transaction voidCancelReverseAuthorization(GlobalpaymentsRequest request) throws ApiException;

    }
