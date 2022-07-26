package ahmed.com.digitalpayment.payments.models;

import java.io.Serializable;

public interface PaymentEnumValue extends Serializable {
    String getType();

    String getCode();

    String getGlobalPayCode();

}
