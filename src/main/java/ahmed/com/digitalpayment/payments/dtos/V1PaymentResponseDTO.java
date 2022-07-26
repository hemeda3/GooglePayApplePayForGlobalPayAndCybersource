package ahmed.com.digitalpayment.payments.dtos;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class V1PaymentResponseDTO {

    private final String orderId;
    private final String transactionId;
    private final String authCode;
    private final String authorizationID;
    private final String captureID;
    private final String refundID;

}
