package ahmed.com.digitalpayment.payments.models;

import lombok.*;

@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GlobalpaymentsRequest {

    private String digitalPaymentToken;
    private String currency;
    private String transactionId;
    private String orderId;
    private String authCode;
    private String amount;
    private WalletType walletType;



}
