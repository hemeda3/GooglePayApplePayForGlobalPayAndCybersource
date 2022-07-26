package ahmed.com.digitalpayment.payments.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CybersourceCaptureRequest  {

    private String authRequestID;
    private String currency;
    private BigDecimal totalAmount;
    private String paymentProvider;
    private String merchantTransactionCode;

    public CybersourceCaptureRequest(String merchantTransactionCode, String authRequestID, String currency, BigDecimal totalAmount, String paymentProvider) {

        this.authRequestID = authRequestID;
            this.currency = currency;
            this.totalAmount = totalAmount;
            this.paymentProvider = paymentProvider;
            this.merchantTransactionCode = merchantTransactionCode;

    }




}
