package ahmed.com.digitalpayment.payments.dtos;


import ahmed.com.digitalpayment.payments.models.WalletType;
import lombok.Data;

@Data
public class V1GlobalPaymentRequestDTO {
    private String digitalPaymentToken;
    private String currency;
    private String transactionId;
    private String orderId;
    private String authCode;
    private String amount;
    private WalletType walletType;
}
