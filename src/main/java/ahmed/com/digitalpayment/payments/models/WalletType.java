package ahmed.com.digitalpayment.payments.models;

public enum WalletType implements PaymentEnumValue
{

	GOOGLE("Google","GOOGLEPAY"),

	APPLE("Apple","APPLEPAY");

	public final static String _TYPECODE = "WalletType";

	public final static String SIMPLE_CLASSNAME = "WalletType";

	private final String code;
	private final String globalPayCode;


	private WalletType(final String code, String globalPayCode)
	{
		this.code = code.intern();
		this.globalPayCode = globalPayCode.intern();
	}

	


	public String getCode()
	{
		return this.code;
	}


	public String getGlobalPayCode()
	{
		return this.globalPayCode;
	}


	public String getType()
	{
		return SIMPLE_CLASSNAME;
	}
	
}
