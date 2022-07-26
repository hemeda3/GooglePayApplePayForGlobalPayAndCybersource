package ahmed.com.digitalpayment.payments.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CybersourceRefundRequest  {

    private String captureRequestID;
    private String authRequestID;
    private String currency;
    private BigDecimal totalAmount;
    private String paymentProvider;
    private String merchantTransactionCode;



}
