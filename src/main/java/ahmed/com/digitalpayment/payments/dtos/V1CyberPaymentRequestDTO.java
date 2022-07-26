
package ahmed.com.digitalpayment.payments.dtos;

import ahmed.com.digitalpayment.payments.models.WalletType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Billing address info
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public  class V1CyberPaymentRequestDTO implements java.io.Serializable
{
	private String city;
	private String country;
	private String email;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String postalCode;
	private String state;
	private String street1;
	private String street2;
	private String ipAddress;
	private String region;
	private String purchaseTotals_currency;
	private String purchaseTotals_grandTotalAmount;
	private String token;
	private String paymentProvider;
	private String creditCardType;
	private WalletType walletType;

}
