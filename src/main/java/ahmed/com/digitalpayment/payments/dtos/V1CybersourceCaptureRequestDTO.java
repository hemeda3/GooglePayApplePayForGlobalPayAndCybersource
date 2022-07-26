package ahmed.com.digitalpayment.payments.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class V1CybersourceCaptureRequestDTO {

    private String authRequestID;
    private String currency;
    private BigDecimal totalAmount;
    private String paymentProvider;
    private String merchantTransactionCode;

    public V1CybersourceCaptureRequestDTO(String merchantTransactionCode, String authRequestID, String currency, BigDecimal totalAmount, String paymentProvider) {

        this.authRequestID = authRequestID;
            this.currency = currency;
            this.totalAmount = totalAmount;
            this.paymentProvider = paymentProvider;
            this.merchantTransactionCode = merchantTransactionCode;

    }




}
