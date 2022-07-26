package ahmed.com.digitalpayment.payments.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class CybersourceReverseRequest {

    private String authRequestID;
    private String currency;
    private BigDecimal totalAmount;
    private String paymentProvider;
    private String merchantTransactionCode;

}
