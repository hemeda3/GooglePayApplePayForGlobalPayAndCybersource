
package ahmed.com.digitalpayment.payments.models;

/**
 * Billing address info
 */
public  class CybersourceAuthRequest implements java.io.Serializable
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

	public CybersourceAuthRequest()
	{
		// default constructor
	}
	
		
	
	public void setCity(final String city)
	{
		this.city = city;
	}

		
	
	public String getCity() 
	{
		return city;
	}
	
		
	
	public void setCountry(final String country)
	{
		this.country = country;
	}

		
	
	public String getCountry() 
	{
		return country;
	}
	
		
	
	public void setEmail(final String email)
	{
		this.email = email;
	}

		
	
	public String getEmail() 
	{
		return email;
	}
	
		
	
	public void setFirstName(final String firstName)
	{
		this.firstName = firstName;
	}

		
	
	public String getFirstName() 
	{
		return firstName;
	}
	
		
	
	public void setLastName(final String lastName)
	{
		this.lastName = lastName;
	}

		
	
	public String getLastName() 
	{
		return lastName;
	}
	
		
	
	public void setPhoneNumber(final String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

		
	
	public String getPhoneNumber() 
	{
		return phoneNumber;
	}
	
		
	
	public void setPostalCode(final String postalCode)
	{
		this.postalCode = postalCode;
	}

		
	
	public String getPostalCode() 
	{
		return postalCode;
	}
	
		
	
	public void setState(final String state)
	{
		this.state = state;
	}

		
	
	public String getState() 
	{
		return state;
	}
	
		
	
	public void setStreet1(final String street1)
	{
		this.street1 = street1;
	}

		
	
	public String getStreet1() 
	{
		return street1;
	}
	
		
	
	public void setStreet2(final String street2)
	{
		this.street2 = street2;
	}

		
	
	public String getStreet2() 
	{
		return street2;
	}
	
		
	
	public void setIpAddress(final String ipAddress)
	{
		this.ipAddress = ipAddress;
	}

		
	
	public String getIpAddress() 
	{
		return ipAddress;
	}
	
		
	
	public void setRegion(final String region)
	{
		this.region = region;
	}

		
	
	public String getRegion() 
	{
		return region;
	}

	public String getPurchaseTotals_currency() {
		return purchaseTotals_currency;
	}

	public void setPurchaseTotals_currency(String purchaseTotals_currency) {
		this.purchaseTotals_currency = purchaseTotals_currency;
	}

	public String getPurchaseTotals_grandTotalAmount() {
		return purchaseTotals_grandTotalAmount;
	}

	public void setPurchaseTotals_grandTotalAmount(String purchaseTotals_grandTotalAmount) {
		this.purchaseTotals_grandTotalAmount = purchaseTotals_grandTotalAmount;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPaymentProvider() {
		return paymentProvider;
	}

	public void setPaymentProvider(String paymentProvider) {
		this.paymentProvider = paymentProvider;
	}

	public String getCreditCardType() {
		return creditCardType;
	}

	public void setCreditCardType(String creditCardType) {
		this.creditCardType = creditCardType;
	}

	public WalletType getWalletType() {
		return walletType;
	}

	public void setWalletType(WalletType walletType) {
		this.walletType = walletType;
	}
}
