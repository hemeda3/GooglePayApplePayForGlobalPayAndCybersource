package ahmed.com.digitalpayment.payments.services;

import ahmed.com.digitalpayment.payments.IPaymentService;
import ahmed.com.digitalpayment.payments.models.*;
import com.global.api.ServicesContainer;
import com.global.api.entities.Transaction;
import com.global.api.entities.enums.MobilePaymentMethodType;
import com.global.api.entities.enums.TransactionModifier;
import com.global.api.entities.enums.TransactionType;
import com.global.api.entities.exceptions.ApiException;
import com.global.api.entities.exceptions.ConfigurationException;
import com.global.api.paymentMethods.CreditCardData;
import com.global.api.paymentMethods.TransactionReference;
import com.global.api.serviceConfigs.GatewayConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;
import java.util.UUID;
@Slf4j
@Component
public class GlobalPaymentsService implements IPaymentService {

    private static final String GP_API_CONFIG_NAME = "GP_API_CONFIG";

    public GlobalPaymentsService(@Value("${digitalpayment.globalpayments.merchantID}") String merchantID,
                                 @Value("${digitalpayment.globalpayments.accountId:internet}")  String accountId,
                                         @Value("${digitalpayment.globalpayments.sharedSecret}") String sharedSecret,
                                         @Value("${digitalpayment.globalpayments.refundSecret}") String refundSecret,
                                         @Value("${digitalpayment.globalpayments.rebateSecret}") String rebateSecret)
                                 throws ConfigurationException {
        GatewayConfig config = new GatewayConfig();

        config.setMerchantId(merchantID);
        config.setAccountId(accountId);
        config.setSharedSecret(sharedSecret);
        config.setRefundPassword(refundSecret);
        config.setRebatePassword(rebateSecret);

        config.setEnableLogging(true);
        ServicesContainer.configureService(config, GP_API_CONFIG_NAME);
    }


    /**
     * amount, currency, walletType
     * @param request
     * @return
     * @throws ApiException
     */
    @Override
    public Transaction authorize(GlobalpaymentsRequest request) throws ApiException {


        String token = new String(Base64.decodeBase64(request.getDigitalPaymentToken()));
        CreditCardData card = new CreditCardData(token);
        card.setMobileType(MobilePaymentMethodType.valueOf(request.getWalletType().getGlobalPayCode()));
        Transaction transaction =
                card
                        .authorize(new BigDecimal(request.getAmount()))
                        .withCurrency(request.getCurrency())
                        .withModifier(TransactionModifier.EncryptedMobile)
                        .withClientTransactionId(UUID.randomUUID().toString())
                        .execute(GP_API_CONFIG_NAME);
        return transaction;
    }



    @Override
    public Transaction capture(GlobalpaymentsRequest request) throws ApiException {

        Transaction transaction1authorizedOnly = Transaction.fromId(request.getTransactionId(), request.getOrderId());
        log.info(" start capturing ");
        Transaction transaction = transaction1authorizedOnly
                .capture().execute(GP_API_CONFIG_NAME);

        return transaction;
    }



    @Override
    public Transaction voidCancelReverseAuthorization(GlobalpaymentsRequest request) throws ApiException {
        Transaction transaction = Transaction.fromId(request.getTransactionId(), request.getOrderId())
                .cancel()
                .execute(GP_API_CONFIG_NAME);
        log.info(transaction.toString());
        return transaction;
    }

    @Override
    public Transaction refund(GlobalpaymentsRequest request) throws ApiException {
        TransactionReference transactionReference = new TransactionReference();
        transactionReference.setTransactionId(request.getTransactionId());
        transactionReference.setOrderId(request.getOrderId());
        transactionReference.setAuthCode(request.getAuthCode());

        Transaction transaction = Transaction.fromId(request.getTransactionId(), request.getOrderId())
                .refund(new BigDecimal(request.getAmount()))
                .withTransactiontype(TransactionType.Refund)
                .withPaymentMethod(transactionReference)
                .withCurrency(request.getCurrency())
                .execute(GP_API_CONFIG_NAME);

        return transaction;

    }

    @Override
    public String voidCancelReverseAuthorization(CybersourceReverseRequest cybersourceReverseRequest) throws ApiException {
        throw new UnsupportedOperationException();
    }
    @Override
    public String capture(CybersourceCaptureRequest request) throws ApiException {
        throw new UnsupportedOperationException();
    }

    @Override
    public String refund(CybersourceRefundRequest captureRequest) throws ApiException {
        throw new UnsupportedOperationException();
    }

    @Override
    public String authorize(CybersourceAuthRequest cybersourceAuthRequest) throws ApiException {
        throw new UnsupportedOperationException();
    }

}
