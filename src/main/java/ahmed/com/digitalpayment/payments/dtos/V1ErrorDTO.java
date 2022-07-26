package ahmed.com.digitalpayment.payments.dtos;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Error message
 */
@ApiModel(value="error", description="Error message")
public  class V1ErrorDTO implements Serializable
{


 	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name="type", value="Type of the error e.g. 'LowStockError'.")
	private String type;

	@ApiModelProperty(name="reason", value="Additional classification specific for each error type e.g. 'noStock'.")
	private String reason;

	@ApiModelProperty(name="message", value="Descriptive, human readable error message.")
	private String message;

	@ApiModelProperty(name="subjectType", value="Type of the object related to the error e.g. 'entry'.")
	private String subjectType;

	@ApiModelProperty(name="subject", value="Identifier of the related object e.g. '1'.")
	private String subject;

	@ApiModelProperty(name="errorCode", value="Error code")
	private String errorCode;
	
	public V1ErrorDTO()
	{
		// default constructor
	}
	
		
	
	public void setType(final String type)
	{
		this.type = type;
	}

		
	
	public String getType() 
	{
		return type;
	}
	
		
	
	public void setReason(final String reason)
	{
		this.reason = reason;
	}

		
	
	public String getReason() 
	{
		return reason;
	}
	
		
	
	public void setMessage(final String message)
	{
		this.message = message;
	}

		
	
	public String getMessage() 
	{
		return message;
	}
	
		
	
	public void setSubjectType(final String subjectType)
	{
		this.subjectType = subjectType;
	}

		
	
	public String getSubjectType() 
	{
		return subjectType;
	}
	
		
	
	public void setSubject(final String subject)
	{
		this.subject = subject;
	}

		
	
	public String getSubject() 
	{
		return subject;
	}
	
		
	
	public void setErrorCode(final String errorCode)
	{
		this.errorCode = errorCode;
	}

		
	
	public String getErrorCode() 
	{
		return errorCode;
	}
	


}
