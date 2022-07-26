package ahmed.com.digitalpayment.payments.models;

public enum CreditCardType implements PaymentEnumValue
{

	AMEX("amex"),

	MAESTRO("maestro"),

	SWITCH("switch"),

	VISA("visa"),

	MASTER("master"),

	MASTERCARD_EUROCARD("mastercard_eurocard"),

	DINERS("diners");
	 
	public final static String _TYPECODE = "CreditCardType";
	
	public final static String SIMPLE_CLASSNAME = "CreditCardType";
	
	private final String code;
	

	private CreditCardType(final String code)
	{
		this.code = code.intern();
	}
	
	

	@Override
	public String getCode()
	{
		return this.code;
	}

	@Override
	public String getGlobalPayCode()
	{
		return this.code;
	}

	/**
	 * Gets the type this enum value belongs to.
	 *  
	 * @return code of type
	 */
	@Override
	public String getType()
	{
		return SIMPLE_CLASSNAME;
	}
	
}
