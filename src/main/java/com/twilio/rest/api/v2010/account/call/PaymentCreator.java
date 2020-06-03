/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.api.v2010.account.call;

import com.twilio.base.Creator;
import com.twilio.converter.Converter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Map;

/**
 * PLEASE NOTE that this class contains preview products that are subject to
 * change. Use them with caution. If you currently do not have developer preview
 * access, please contact help@twilio.com.
 */
public class PaymentCreator extends Creator<Payment> {
    private String pathAccountSid;
    private final String pathCallSid;
    private final String idempotencyKey;
    private final URI statusCallback;
    private Payment.BankAccountType bankAccountType;
    private BigDecimal chargeAmount;
    private String currency;
    private String description;
    private String input;
    private Integer minPostalCodeLength;
    private Map<String, Object> parameter;
    private String paymentConnector;
    private Payment.PaymentMethod paymentMethod;
    private Boolean postalCode;
    private Boolean securityCode;
    private Integer timeout;
    private Payment.TokenType tokenType;
    private String validCardTypes;

    /**
     * Construct a new PaymentCreator.
     *
     * @param pathCallSid The SID of the call that will create the resource.
     * @param idempotencyKey A unique token for each payment session that should be
     *                       provided to maintain idempotency of the session.
     * @param statusCallback The URL we should call to send status of payment
     *                       session.
     */
    public PaymentCreator(final String pathCallSid,
                          final String idempotencyKey,
                          final URI statusCallback) {
        this.pathCallSid = pathCallSid;
        this.idempotencyKey = idempotencyKey;
        this.statusCallback = statusCallback;
    }

    /**
     * Construct a new PaymentCreator.
     *
     * @param pathAccountSid The SID of the Account that will create the resource
     * @param pathCallSid The SID of the call that will create the resource.
     * @param idempotencyKey A unique token for each payment session that should be
     *                       provided to maintain idempotency of the session.
     * @param statusCallback The URL we should call to send status of payment
     *                       session.
     */
    public PaymentCreator(final String pathAccountSid,
                          final String pathCallSid,
                          final String idempotencyKey,
                          final URI statusCallback) {
        this.pathAccountSid = pathAccountSid;
        this.pathCallSid = pathCallSid;
        this.idempotencyKey = idempotencyKey;
        this.statusCallback = statusCallback;
    }

    /**
     * If Payment source is ACH, type of bank account. Can be: `consumer-checking`,
     * `consumer-savings`, `commercial-checking`. The default value is
     * `consumer-checking`..
     *
     * @param bankAccountType If Payment source is ACH, type of bank account.
     * @return this
     */
    public PaymentCreator setBankAccountType(final Payment.BankAccountType bankAccountType) {
        this.bankAccountType = bankAccountType;
        return this;
    }

    /**
     * If this field is present and greater than `0.0` payment source will be
     * charged. Otherwise payment source will be tokenized..
     *
     * @param chargeAmount If this field is present and greater than `0.0` payment
     *                     source will be charged.
     * @return this
     */
    public PaymentCreator setChargeAmount(final BigDecimal chargeAmount) {
        this.chargeAmount = chargeAmount;
        return this;
    }

    /**
     * Currency `charge_amount` is in. It's format should be as specified in [ISO
     * 4127](http://www.iso.org/iso/home/standards/currency_codes.htm) format. The
     * default value is `USD`..
     *
     * @param currency Currency `charge_amount` is in.
     * @return this
     */
    public PaymentCreator setCurrency(final String currency) {
        this.currency = currency;
        return this;
    }

    /**
     * Decription of the charge..
     *
     * @param description Decription of the charge.
     * @return this
     */
    public PaymentCreator setDescription(final String description) {
        this.description = description;
        return this;
    }

    /**
     * Kind of medium customer would enter payment source information in. Currently
     * only 'DTMF' is supported, which means customer would use keypad of their
     * phone to enter card number etc..
     *
     * @param input Kind of medium customer would enter payment source information
     *              in.
     * @return this
     */
    public PaymentCreator setInput(final String input) {
        this.input = input;
        return this;
    }

    /**
     * If postal code is expected, minimum length of the postal code. When user
     * enters postal code, this value will be used to validate..
     *
     * @param minPostalCodeLength If postal code is expected, minimum length of the
     *                            postal code.
     * @return this
     */
    public PaymentCreator setMinPostalCodeLength(final Integer minPostalCodeLength) {
        this.minPostalCodeLength = minPostalCodeLength;
        return this;
    }

    /**
     * Additonal data to be sent over to payment provider. It has to be a JSON
     * string with only one level object. This parameter can be used to send
     * information such as customer name, phone number etc. Refer to specific
     * payment provider's documentation in Twilio console for supported
     * names/values/format..
     *
     * @param parameter Additonal data to be sent over to payment provider.
     * @return this
     */
    public PaymentCreator setParameter(final Map<String, Object> parameter) {
        this.parameter = parameter;
        return this;
    }

    /**
     * Payment connector that you would like Twilio to use for processing payments.
     * The default value is `Default`, which means you need to have at least one
     * payment connector configured in Twilio with name 'Default'. If not you must
     * provide connector configuration name here..
     *
     * @param paymentConnector Payment connector that you would like Twilio to use
     *                         for processing payments.
     * @return this
     */
    public PaymentCreator setPaymentConnector(final String paymentConnector) {
        this.paymentConnector = paymentConnector;
        return this;
    }

    /**
     * Payment source type. Can be: `credit-card`, `ach-debit`. The default value is
     * `credit-card`..
     *
     * @param paymentMethod Payment source type.
     * @return this
     */
    public PaymentCreator setPaymentMethod(final Payment.PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    /**
     * Whether to expect postal code during payment source data gathering. Can be:
     * `true`, `false`. The default value is `true`..
     *
     * @param postalCode Whether to expect postal code during payment source data
     *                   gathering.
     * @return this
     */
    public PaymentCreator setPostalCode(final Boolean postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    /**
     * Whether to expect security code during payment source data gathering. Can be:
     * `true`, `false`. The default value is `true`..
     *
     * @param securityCode Whether to expect security code during payment source
     *                     data gathering.
     * @return this
     */
    public PaymentCreator setSecurityCode(final Boolean securityCode) {
        this.securityCode = securityCode;
        return this;
    }

    /**
     * The number of seconds that we should allow customer to enter payment
     * information. Can be an integer between `5` and `600`, inclusive. The default
     * value is `5`..
     *
     * @param timeout The number of seconds that we should allow customer to enter
     *                payment information
     * @return this
     */
    public PaymentCreator setTimeout(final Integer timeout) {
        this.timeout = timeout;
        return this;
    }

    /**
     * If tokenization of payment source is desired, this represents type of token.
     * Can be: `one-time`, `reusable`. The default value is `reusable`..
     *
     * @param tokenType If tokenization of payment source is desired, this
     *                  represents type of token.
     * @return this
     */
    public PaymentCreator setTokenType(final Payment.TokenType tokenType) {
        this.tokenType = tokenType;
        return this;
    }

    /**
     * List of card types accepted with each card types separated by space. Can be:
     * `visa`,`nmastercard`,`amex`,`maestro`,`discover`,`optima`,`jcb`,`diners-club`,`enroute`. The default value is `visa mastercard amex`..
     *
     * @param validCardTypes List of card types accepted with each card types
     *                       separated by space.
     * @return this
     */
    public PaymentCreator setValidCardTypes(final String validCardTypes) {
        this.validCardTypes = validCardTypes;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created Payment
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Payment create(final TwilioRestClient client) {
        this.pathAccountSid = this.pathAccountSid == null ? client.getAccountSid() : this.pathAccountSid;
        Request request = new Request(
            HttpMethod.POST,
            Domains.API.toString(),
            "/2010-04-01/Accounts/" + this.pathAccountSid + "/Calls/" + this.pathCallSid + "/Payments.json"
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Payment creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
            throw new ApiException(restException);
        }

        return Payment.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (idempotencyKey != null) {
            request.addPostParam("IdempotencyKey", idempotencyKey);
        }

        if (statusCallback != null) {
            request.addPostParam("StatusCallback", statusCallback.toString());
        }

        if (bankAccountType != null) {
            request.addPostParam("BankAccountType", bankAccountType.toString());
        }

        if (chargeAmount != null) {
            request.addPostParam("ChargeAmount", chargeAmount.toString());
        }

        if (currency != null) {
            request.addPostParam("Currency", currency);
        }

        if (description != null) {
            request.addPostParam("Description", description);
        }

        if (input != null) {
            request.addPostParam("Input", input);
        }

        if (minPostalCodeLength != null) {
            request.addPostParam("MinPostalCodeLength", minPostalCodeLength.toString());
        }

        if (parameter != null) {
            request.addPostParam("Parameter", Converter.mapToJson(parameter));
        }

        if (paymentConnector != null) {
            request.addPostParam("PaymentConnector", paymentConnector);
        }

        if (paymentMethod != null) {
            request.addPostParam("PaymentMethod", paymentMethod.toString());
        }

        if (postalCode != null) {
            request.addPostParam("PostalCode", postalCode.toString());
        }

        if (securityCode != null) {
            request.addPostParam("SecurityCode", securityCode.toString());
        }

        if (timeout != null) {
            request.addPostParam("Timeout", timeout.toString());
        }

        if (tokenType != null) {
            request.addPostParam("TokenType", tokenType.toString());
        }

        if (validCardTypes != null) {
            request.addPostParam("ValidCardTypes", validCardTypes);
        }
    }
}