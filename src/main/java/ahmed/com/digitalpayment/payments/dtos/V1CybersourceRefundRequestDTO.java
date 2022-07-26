package ahmed.com.digitalpayment.payments.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class V1CybersourceRefundRequestDTO  {

    private String captureRequestID;
    private String authRequestID;
    private String currency;
    private BigDecimal totalAmount;
    private String paymentProvider;
    private String merchantTransactionCode;

}
